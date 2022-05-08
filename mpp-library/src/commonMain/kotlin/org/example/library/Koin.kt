package org.example.library

import dev.icerock.moko.resources.desc.ResourceFormatted
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.units.TableUnitItem
import io.github.aakira.napier.Napier
import org.example.library.domain.di.domainModule
import org.example.library.domain.entity.News
import org.example.library.domain.repository.ConfigRepository
import org.example.library.domain.repository.NewsRepository
import org.example.library.feature.config.di.configModule
import org.example.library.feature.config.model.ConfigStore
import org.example.library.feature.config.presentation.ConfigViewModel
import org.example.library.feature.list.di.listModule
import org.example.library.feature.list.model.ListSource
import org.example.library.feature.list.presentation.ListViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

interface NewsUnitsFactory {
    fun createNewsTile(
        id: Long,
        title: String,
        description: StringDesc
    ): TableUnitItem
}

private fun sharedNewsModule() = module {
    single {
        val newsRepository: NewsRepository = get()
        object : ListSource<News> {
            override suspend fun getList(): List<News> {
                return newsRepository.getNewsList()
            }
        }
    }
    single {
        object : ListViewModel.Strings {
            override val unknownError = MR.strings.unknown_error
        }
    }
    single {
        val newsUnitsFactory: NewsUnitsFactory = get()
        object : ListViewModel.UnitsFactory<News> {
            override fun createTile(data: News): TableUnitItem {
                return newsUnitsFactory.createNewsTile(
                    id = data.id.toLong(),
                    title = data.fullName.orEmpty(),
                    description = data.description?.desc() ?: MR.strings.no_description.desc()
                )
            }
        }
    }
}

private fun sharedConfigModule() = module {
    single<ConfigStore> {
        val configRepository: ConfigRepository = get()
        object : ConfigStore {
            override var apiToken: String?
                get() = configRepository.apiToken
                set(value) { configRepository.apiToken = value }
            override var language: String?
                get() = configRepository.language
                set(value) { configRepository.language = value }
        }
    }
    single<ConfigViewModel.Validations> {
        object : ConfigViewModel.Validations {
            override fun validateToken(value: String): StringDesc? {
                return if (value.isBlank()) {
                    MR.strings.invalid_token.desc()
                } else {
                    null
                }
            }

            override fun validateLanguage(value: String): StringDesc? {
                val validValues = listOf("ru", "us")
                return if (validValues.contains(value)) {
                    null
                } else {
                    StringDesc.ResourceFormatted(
                        MR.strings.invalid_language_s,
                        validValues.joinToString(", ")
                    )
                }
            }
        }
    }
    single(qualifier = StringQualifier("DefaultLanguage")) {
        "us"
    }
    single(qualifier = StringQualifier("DefaultToken")) {
        "ed155d0a445e4b4fbd878fe1f3bc1b7f"
    }
}

fun startLibraryKoin(vararg modules: Module): KoinApplication {
    return startKoin {
        modules(
            *modules,
            domainModule(),
            sharedNewsModule(),
            listModule<News>(),
            sharedConfigModule(),
            configModule()
        )
    }.apply {
        Napier.base(koin.get())
    }
}
