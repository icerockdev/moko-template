package org.example.library.domain.di

import dev.icerock.moko.network.exceptionfactory.HttpExceptionFactory
import dev.icerock.moko.network.exceptionfactory.parser.ErrorExceptionParser
import dev.icerock.moko.network.exceptionfactory.parser.ValidationExceptionParser
import dev.icerock.moko.network.features.ExceptionFeature
import dev.icerock.moko.network.features.TokenFeature
import dev.icerock.moko.network.generated.apis.NewsApi
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.Json
import org.example.library.domain.repository.ConfigRepository
import org.example.library.domain.repository.NewsRepository
import org.example.library.domain.storage.KeyValueStorage
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

val BaseUrlQualifier = StringQualifier("BaseUrl")
fun domainModule() = module {
    single {
        Json {
            ignoreUnknownKeys = true
        }
    }
    single {
        KeyValueStorage(get())
    }

    single {
        val keyValueStorage: KeyValueStorage = get()
        val json: Json = get()

        val config: HttpClientConfig<*>.() -> Unit = {
            install(ExceptionFeature) {
                exceptionFactory = HttpExceptionFactory(
                    defaultParser = ErrorExceptionParser(json),
                    customParsers = mapOf(
                        HttpStatusCode.UnprocessableEntity.value to ValidationExceptionParser(json)
                    )
                )
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.d(message = message)
                    }
                }
                level = LogLevel.HEADERS
            }
            install(TokenFeature) {
                tokenHeaderName = "Authorization"
                tokenProvider = object : TokenFeature.TokenProvider {
                    override fun getToken(): String? = keyValueStorage.token
                }
            }

            // disable standard BadResponseStatus - exceptionfactory do it for us
            expectSuccess = false
        }

        val httpClientEngine: HttpClientEngine? = getOrNull()
        if (httpClientEngine != null) HttpClient(httpClientEngine, config)
        else HttpClient(config)
    }

    single {
        NewsApi(
            basePath = get(BaseUrlQualifier),
            httpClient = get(),
            json = get()
        )
    }

    single {
        NewsRepository(get(), get())
    }
    single {
        ConfigRepository(get())
    }
}
