/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library

import com.russhwolf.settings.Settings
import dev.icerock.moko.resources.StringResource
import org.example.library.feature.news.model.News
import org.example.library.feature.news.model.NewsSource
import org.example.library.feature.news.presentation.NewsListViewModel
import org.example.library.domain.di.Factory as DomainFactory
import org.example.library.feature.news.di.Factory as NewsFactory

class Factory(
    settings: Settings,
    baseUrl: String
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
        }
    )
}