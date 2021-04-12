/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library

import com.russhwolf.settings.MockSettings
import com.russhwolf.settings.Settings

internal fun createSharedFactory(
    settings: Settings = MockSettings()
): SharedFactory {
    return SharedFactory(
        settings = settings,
        antilog = TestAntilog(),
        baseUrl = "https://localhost",
        newsUnitsFactory =
    )
}

class TestUnitFactory : NewsUnitsFactory {

}
