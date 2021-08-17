/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.app.view

import android.content.Intent
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import dev.icerock.moko.mvvm.MvvmEventsActivity
import dev.icerock.moko.mvvm.createViewModelFactory
import dev.icerock.moko.mvvm.dispatcher.eventsDispatcherOnMain
import javax.inject.Inject
import org.example.app.BR
import org.example.app.R
import org.example.app.databinding.ActivityConfigBinding
import org.example.library.feature.config.di.ConfigFactory
import org.example.library.feature.config.presentation.ConfigViewModel

// MvvmEventsActivity for simplify creation of MVVM screen with https://github.com/icerockdev/moko-mvvm
@AndroidEntryPoint
class ConfigActivity :
    MvvmEventsActivity<ActivityConfigBinding, ConfigViewModel, ConfigViewModel.EventsListener>(),
    ConfigViewModel.EventsListener {

    override val layoutId: Int = R.layout.activity_config
    override val viewModelClass: Class<ConfigViewModel> = ConfigViewModel::class.java
    override val viewModelVariableId: Int = BR.viewModel

    @Inject
    lateinit var factory: ConfigFactory

    // createViewModelFactory is extension from https://github.com/icerockdev/moko-mvvm
    // ViewModel not recreating at configuration changes
    override fun viewModelFactory(): ViewModelProvider.Factory = createViewModelFactory {
        factory.createConfigViewModel(
            eventsDispatcher = eventsDispatcherOnMain()
        )
    }

    // route called by EventsDispatcher from ViewModel (https://github.com/icerockdev/moko-mvvm)
    override fun routeToNews() {
        setContent{
            testConfigActivity()
        }
        Intent(this, NewsActivity::class.java).also { startActivity(it) }
    }
}

@Composable
fun testConfigActivity() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
    ) {
        val textState1 = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            value = textState1.value,
            onValueChange = { textState1.value = it }
        )

        val textState2 = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            value = textState2.value,
            onValueChange = { textState2.value = it }
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            onClick = {  }) {
        }
    }
}
