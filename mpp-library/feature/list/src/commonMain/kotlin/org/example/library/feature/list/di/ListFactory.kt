/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library.feature.list.di

import org.example.library.feature.list.model.ListSource
import org.example.library.feature.list.presentation.ListViewModel

class ListFactory<T>(
    private val listSource: ListSource<T>,
    private val strings: ListViewModel.Strings,
    private val unitsFactory: ListViewModel.UnitsFactory<T>
) {
    fun createListViewModel(): ListViewModel<T> {
        return ListViewModel(
            listSource = listSource,
            strings = strings,
            unitsFactory = unitsFactory
        )
    }
}
