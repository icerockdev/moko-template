/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library

import com.russhwolf.settings.Settings
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import org.example.library.domain.di.DomainFactory
import org.example.library.feature.auth.di.AuthFactory
import org.example.library.feature.auth.model.AuthStore
import org.example.library.feature.auth.presentation.AuthViewModel
import org.example.library.feature.news.di.NewsFactory
import org.example.library.feature.news.model.News
import org.example.library.feature.news.model.NewsSource
import org.example.library.feature.news.presentation.NewsListViewModel

class SharedFactory(
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
        authStore = object : AuthStore {
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
        validations = object : AuthViewModel.Validations {
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
        },
        defaultToken = "ed155d0a445e4b4fbd878fe1f3bc1b7f",
        defaultLanguage = "us"
    )
}