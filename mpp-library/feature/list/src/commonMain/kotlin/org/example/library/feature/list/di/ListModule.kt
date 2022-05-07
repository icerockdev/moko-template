package org.example.library.feature.list.di

import org.example.library.feature.list.presentation.ListViewModel
import org.koin.dsl.module

fun <T> listModule() = module {
    factory {
        ListViewModel<T>(
            listSource = get(),
            strings = get(),
            unitsFactory = get()
        )
    }
}
