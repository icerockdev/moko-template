/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.app

import android.app.Application
import android.content.Context
import com.russhwolf.settings.AndroidSettings
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.units.UnitItem
import org.example.library.Factory
import org.example.library.feature.news.presentation.NewsListViewModel

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        factory = Factory(
            baseUrl = BuildConfig.BASE_URL,
            settings = AndroidSettings(getSharedPreferences("app", Context.MODE_PRIVATE)),
            newsUnitsFactory = object: NewsListViewModel.UnitsFactory {
                override fun createNewsTile(
                    id: Long,
                    title: String,
                    description: StringDesc
                ): UnitItem {
                    return TileNews().apply {
                        itemId = id
                        this.title = title
                        this.description = description
                    }
                }
            }
        )
    }

    companion object {
        lateinit var factory: Factory
    }
}