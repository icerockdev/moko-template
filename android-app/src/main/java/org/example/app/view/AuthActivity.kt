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
import org.example.app.MainApplication
import org.example.app.R
import org.example.app.databinding.ActivityAuthBinding
import org.example.library.feature.auth.presentation.AuthViewModel

class AuthActivity :
    MvvmEventsActivity<ActivityAuthBinding, AuthViewModel, AuthViewModel.EventsListener>(),
    AuthViewModel.EventsListener {

    override val layoutId: Int = R.layout.activity_auth
    override val viewModelClass: Class<AuthViewModel> = AuthViewModel::class.java
    override val viewModelVariableId: Int = BR.viewModel

    override fun viewModelFactory(): ViewModelProvider.Factory = createViewModelFactory {
        MainApplication.factory.authFactory.createAuthViewModel(
            eventsDispatcher = eventsDispatcherOnMain()
        )
    }

    override fun routeToNews() {
        Intent(this, NewsActivity::class.java).also { startActivity(it) }
    }
}