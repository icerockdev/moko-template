/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library.feature.news.di

import org.example.library.feature.news.model.NewsSource
import org.example.library.feature.news.presentation.NewsListViewModel

class Factory(
    private val newsSource: NewsSource,
    private val newsListStrings: NewsListViewModel.Strings
) {
    fun createNewsListViewModel(): NewsListViewModel {
        return NewsListViewModel(
            newsSource = newsSource,
            strings = newsListStrings
        )
    }
}