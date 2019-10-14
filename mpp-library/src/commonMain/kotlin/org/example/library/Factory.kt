/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library

import com.russhwolf.settings.Settings
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import org.example.library.feature.auth.model.LoginStorage
import org.example.library.feature.auth.presentation.LoginViewModel
import org.example.library.feature.news.model.News
import org.example.library.feature.news.model.NewsSource
import org.example.library.feature.news.presentation.NewsListViewModel
import org.example.library.domain.di.Factory as DomainFactory
import org.example.library.feature.auth.di.Factory as AuthFactory
import org.example.library.feature.news.di.Factory as NewsFactory

class Factory(
    settings: Settings,
    baseUrl: String,
    newsUnitsFactory: NewsListViewModel.UnitsFactory
) {
    private val domainFactory = DomainFactory(
        settings = settings,
        baseUrl = baseUrl
    )

    val newsFactory = NewsFactory(
        newsSource = object : NewsSource {
            override suspend fun getNewsList(): List<News> {
                return domainFactory.newsRepository.getNewsList().map { item ->
                    News(
                        id = item.id.toLong(),
                        title = item.fullName.orEmpty(),
                        description = item.description
                    )
                }
            }
        },
        newsListStrings = object : NewsListViewModel.Strings {
            override val unknownError: StringResource = MR.strings.unknown_error
            override val noDescription: StringResource = MR.strings.no_description
        },
        newsUnitsFactory = newsUnitsFactory
    )

    val authFactory = AuthFactory(
        loginStorage = object : LoginStorage {
            override var apiToken: String?
                get() = domainFactory.authRepository.apiToken
                set(value) {
                    domainFactory.authRepository.apiToken = value
                }
            override var language: String?
                get() = domainFactory.authRepository.language
                set(value) {
                    domainFactory.authRepository.language = value
                }
        },
        loginValidations = object : LoginViewModel.Validations {
            override fun validateToken(value: String): StringDesc? {
                return if(value.isBlank()) {
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
        },
        defaultToken = "ed155d0a445e4b4fbd878fe1f3bc1b7f",
        defaultLanguage = "us"
    )
}