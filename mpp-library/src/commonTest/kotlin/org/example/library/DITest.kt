/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library

import com.russhwolf.settings.MockSettings
import io.ktor.http.parametersOf
import org.koin.test.check.checkModules
import kotlin.test.Test

class DITest {
    @Test
    fun test() {
        startTestKoin(MockSettings()).checkModules()
    }
}
