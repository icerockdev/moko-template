/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.app.view

import androidx.lifecycle.ViewModelProvider
import dev.icerock.moko.mvvm.MvvmActivity
import dev.icerock.moko.mvvm.createViewModelFactory
import org.example.app.AppComponent
import org.example.app.BR
import org.example.app.R
import org.example.app.databinding.ActivityNewsBinding
import org.example.library.feature.news.presentation.NewsListViewModel

// MvvmActivity for simplify creation of MVVM screen with https://github.com/icerockdev/moko-mvvm
class NewsActivity : MvvmActivity<ActivityNewsBinding, NewsListViewModel>() {
    override val layoutId: Int = R.layout.activity_news
    override val viewModelClass: Class<NewsListViewModel> = NewsListViewModel::class.java
    override val viewModelVariableId: Int = BR.viewModel

    // createViewModelFactory is extension from https://github.com/icerockdev/moko-mvvm
    // ViewModel not recreating at configuration changes
    override fun viewModelFactory(): ViewModelProvider.Factory = createViewModelFactory {
        AppComponent.factory.newsFactory.createNewsListViewModel()
    }
}
