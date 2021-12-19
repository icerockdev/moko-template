/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.example.app.units.NewsListUnitsFactory
import org.example.library.SharedFactory

@InstallIn(SingletonComponent::class)
@Module
interface UnitFactoryModule {
    @Binds
    fun bindNewsListUnitFactory(impl: NewsListUnitsFactory): SharedFactory.NewsUnitsFactory
}
