/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.app.di

import android.content.Context
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.Settings
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * module to provide android preference settings for shared multiplatform factory
 */
fun settingsModule() = module {
    single<Settings> {
        AndroidSettings(
            delegate = androidContext().getSharedPreferences("app", Context.MODE_PRIVATE)
        )
    }
}
