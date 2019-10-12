/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library.feature.news.presentation

import dev.icerock.moko.mvvm.State
import dev.icerock.moko.mvvm.asState
import dev.icerock.moko.mvvm.livedata.*
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.units.UnitItem
import kotlinx.coroutines.launch
import org.example.library.feature.news.model.News
import org.example.library.feature.news.model.NewsSource

class NewsListViewModel(
    private val newsSource: NewsSource,
    private val strings: Strings,
    private val unitsFactory: UnitsFactory
) : ViewModel() {

    private val _state: MutableLiveData<State<List<News>, Throwable>> =
        MutableLiveData(initialValue = State.Loading())

    val state: LiveData<State<List<UnitItem>, StringDesc>> = _state
        .dataTransform {
            map { news ->
                news.map { item ->
                    unitsFactory.createNewsTile(
                        id = item.id,
                        title = item.title,
                        description = item.description?.desc() ?: strings.noDescription.desc()
                    )
                }
            }
        }
        .errorTransform {
            map { it.message?.desc() ?: strings.unknownError.desc() }
        }

    init {
        loadList()
    }

    fun onRetryPressed() {
        loadList()
    }

    fun onRefresh() {
        loadList()
    }

    private fun loadList() {
        coroutineScope.launch {
            try {
                _state.value = State.Loading()

                val items = newsSource.getNewsList()

                _state.value = items.asState()
            } catch (error: Throwable) {
                _state.value = State.Error(error)
            }
        }
    }

    interface UnitsFactory {
        fun createNewsTile(
            id: Long,
            title: String,
            description: StringDesc
        ): UnitItem
    }

    interface Strings {
        val unknownError: StringResource
        val noDescription: StringResource
    }
}
