/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library.domain.entity

import dev.icerock.moko.parcelize.Parcelable
import dev.icerock.moko.parcelize.Parcelize
import dev.icerock.moko.network.generated.models.Article

@Parcelize
data class News(
    val id: Int,
    val fullName: String?,
    val description: String?
) : Parcelable

internal fun Article.toDomain(): News = News(
    id = this.url.hashCode(),
    fullName = this.title,
    description = this.description
)
