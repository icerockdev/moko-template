/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.app

import android.app.Application
import android.content.Context
import com.github.aakira.napier.DebugAntilog
import com.russhwolf.settings.AndroidSettings
import org.example.app.units.NewsListUnitsFactory
import org.example.library.SharedFactory

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // create factory of shared module - it's main DI component of application.
        // Provide ViewModels of all features.
        // Input is platform-specific:
        // * baseUrl - server url from platform build configs (allows use buildFlavors in configurations for server)
        // * settings - settings platform storage for https://github.com/russhwolf/multiplatform-settings
        // * antilog - platform logger with LogCat for https://github.com/AAkira/Napier
        // * newsUnitsFactory - platform factory of RecyclerView items for https://github.com/icerockdev/moko-units
        AppComponent.factory = SharedFactory(
            baseUrl = BuildConfig.BASE_URL,
            settings = AndroidSettings(getSharedPreferences("app", Context.MODE_PRIVATE)),
            antilog = DebugAntilog(),
            newsUnitsFactory = NewsListUnitsFactory()
        )
    }
}
