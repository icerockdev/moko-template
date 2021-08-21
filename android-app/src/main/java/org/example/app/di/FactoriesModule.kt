/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.example.library.SharedFactory
import org.example.library.domain.entity.News
import org.example.library.feature.config.di.ConfigFactory
import org.example.library.feature.list.di.ListFactory

@InstallIn(ActivityComponent::class)
@Module
object FactoriesModule {
    @Provides
    fun provideNewsFactory(sharedFactory: SharedFactory): ListFactory<News> =
        sharedFactory.newsFactory

    @Provides
    fun provideConfigFactory(sharedFactory: SharedFactory): ConfigFactory =
        sharedFactory.configFactory
}
