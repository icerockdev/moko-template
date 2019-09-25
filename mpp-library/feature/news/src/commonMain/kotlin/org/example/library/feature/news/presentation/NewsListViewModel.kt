/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library.feature.news.presentation

import dev.icerock.moko.mvvm.State
import dev.icerock.moko.mvvm.asState
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.livedata.errorTransform
import dev.icerock.moko.mvvm.livedata.map
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import kotlinx.coroutines.launch
import org.example.library.feature.news.model.News
import org.example.library.feature.news.model.NewsSource

class NewsListViewModel(
    private val newsSource: NewsSource
) : ViewModel() {

    private val _state: MutableLiveData<State<List<News>, Throwable>> =
        MutableLiveData(initialValue = State.Loading())

    val state: LiveData<State<List<News>, StringDesc>> = _state
        .errorTransform {
            map { it.message ?: "unknown error" }
                .map { it.desc() as StringDesc }
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
}
