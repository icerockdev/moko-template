/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library.feature.news.model

import dev.icerock.moko.core.Parcelable
import dev.icerock.moko.core.Parcelize

@Parcelize
data class News(
    val id: Long,
    val title: String,
    val description: String?
) : Parcelable