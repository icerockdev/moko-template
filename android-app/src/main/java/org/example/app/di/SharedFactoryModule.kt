/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.app.di

import com.russhwolf.settings.Settings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.aakira.napier.Antilog
import javax.inject.Singleton
import org.example.app.BuildConfig
import org.example.library.SharedFactory

/**
 * Module, that provides shared mutliplatform factory for Android platform
 */
@InstallIn(SingletonComponent::class)
@Module
object SharedFactoryModule {
    @Provides
    @Singleton
    fun provideMultiplatformFactory(
        settings: Settings,
        antilog: Antilog,
        newsUnitFactory: SharedFactory.NewsUnitsFactory
    ): SharedFactory = SharedFactory(
        settings = settings,
        antilog = antilog,
        newsUnitsFactory = newsUnitFactory,
        baseUrl = BuildConfig.BASE_URL
    )
}
