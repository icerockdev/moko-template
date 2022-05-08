/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.app.view

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import dev.icerock.moko.mvvm.MvvmEventsActivity
import dev.icerock.moko.mvvm.createViewModelFactory
import dev.icerock.moko.mvvm.dispatcher.eventsDispatcherOnMain
import org.example.app.BR
import org.example.app.R
import org.example.app.databinding.ActivityConfigBinding
import org.example.library.feature.config.di.ConfigFactory
import org.example.library.feature.config.presentation.ConfigViewModel
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

// MvvmEventsActivity for simplify creation of MVVM screen with https://github.com/icerockdev/moko-mvvm
class ConfigActivity :
    MvvmEventsActivity<ActivityConfigBinding, ConfigViewModel, ConfigViewModel.EventsListener>(),
    ConfigViewModel.EventsListener, KoinComponent {

    override val layoutId: Int = R.layout.activity_config
    override val viewModelClass: Class<ConfigViewModel> = ConfigViewModel::class.java
    override val viewModelVariableId: Int = BR.viewModel

    // createViewModelFactory is extension from https://github.com/icerockdev/moko-mvvm
    // ViewModel not recreating at configuration changes
    override fun viewModelFactory(): ViewModelProvider.Factory = createViewModelFactory {
        get<ConfigViewModel> { parametersOf(eventsDispatcherOnMain<ConfigViewModel.EventsListener>()) }
    }

    // route called by EventsDispatcher from ViewModel (https://github.com/icerockdev/moko-mvvm)
    override fun routeToNews() {
        Intent(this, NewsActivity::class.java).also { startActivity(it) }
    }
}
