package org.example.app.di

import org.example.app.BuildConfig
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

fun baseUrlModule() = module {
    single(StringQualifier("BaseUrl")) {
        BuildConfig.BASE_URL
    }
}
