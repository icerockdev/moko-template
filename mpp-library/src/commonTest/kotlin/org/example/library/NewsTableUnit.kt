/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library

import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.units.test.TableUnitItemMock

data class NewsTableUnit(
    override val id: Long,
    val title: String,
    val description: StringDesc
) : TableUnitItemMock()
