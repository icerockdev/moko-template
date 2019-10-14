/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library.domain.repository

import dev.icerock.moko.network.generated.apis.NewsApi
import org.example.library.domain.entity.News
import org.example.library.domain.entity.toDomain
import org.example.library.domain.storage.KeyValueStorage

class NewsRepository internal constructor(
    private val newsApi: NewsApi,
    private val keyValueStorage: KeyValueStorage
) {
    suspend fun getNewsList(query: String? = null, page: Int = 1, pageSize: Int = 20): List<News> {
        return newsApi.topHeadlinesGet(
            country = keyValueStorage.language,
            category = null,
            q = query,
            page = page,
            pageSize = pageSize
        ).articles.map { it.toDomain() }
    }
}
