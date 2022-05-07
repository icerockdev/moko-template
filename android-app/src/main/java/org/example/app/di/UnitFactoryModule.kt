/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.app.di

import org.example.app.units.NewsListUnitsFactory
import org.example.library.NewsUnitsFactory
import org.koin.dsl.module

fun unitsFactoryModule() = module {
    single<NewsUnitsFactory> {
        NewsListUnitsFactory()
    }
}
