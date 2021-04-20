/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library

import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.units.TableUnitItem

class TestUnitFactory : SharedFactory.NewsUnitsFactory {
    override fun createNewsTile(id: Long, title: String, description: StringDesc): TableUnitItem {
        return NewsTableUnit(
            id = id,
            title = title,
            description = description
        )
    }
}
