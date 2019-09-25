/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library.feature.news.model

interface NewsSource {
    suspend fun getNewsList(): List<News>
}