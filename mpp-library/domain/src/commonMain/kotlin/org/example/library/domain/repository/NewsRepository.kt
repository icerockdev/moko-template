/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library.domain.repository

import com.github.aakira.napier.Napier
import dev.icerock.moko.network.generated.apis.NewsApi
import org.example.library.domain.entity.News
import org.example.library.domain.entity.toDomain
import org.example.library.domain.storage.KeyValueStorage
import org.example.sql.AppDatabase

class NewsRepository internal constructor(
    private val newsApi: NewsApi,
    private val keyValueStorage: KeyValueStorage,
    private val appDatabase: AppDatabase
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

    fun testDataBase() {
        val queries = appDatabase.languageQueries

        appDatabase.transaction {
            queries.insertLanguage(id = 1, name = "russian")
            queries.insertLanguage(id = 2, name = "english")
        }

        val items = queries.selectAllLanguages().executeAsList()
        Napier.d("result: $items")

        appDatabase.transaction {
            queries.deleteAllLanguages()
        }
    }
}
