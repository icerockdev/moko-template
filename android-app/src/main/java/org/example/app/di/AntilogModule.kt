/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.app.di

import io.github.aakira.napier.DebugAntilog
import org.koin.dsl.module

/**
 * module to provide Antilog for multiplatform factory
 */
fun antilogModule() = module {
    single {
        DebugAntilog()
    }
}
