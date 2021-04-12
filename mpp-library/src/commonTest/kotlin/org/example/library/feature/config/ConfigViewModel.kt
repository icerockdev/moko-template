/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library.feature.config

import com.russhwolf.settings.MockSettings
import com.russhwolf.settings.Settings
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.test.TestViewModelScope
import dev.icerock.moko.mvvm.test.createTestEventsDispatcher
import dev.icerock.moko.test.AndroidArchitectureInstantTaskExecutorRule
import dev.icerock.moko.test.TestRule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.example.library.SharedFactory
import org.example.library.createSharedFactory
import org.example.library.feature.config.presentation.ConfigViewModel
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ConfigViewModelTests {
    @get:TestRule
    val instantTaskExecutorRule = AndroidArchitectureInstantTaskExecutorRule()

    private lateinit var settings: MockSettings
    private lateinit var listenerEvents: List<String>
    private lateinit var viewModel: ConfigViewModel

    @BeforeTest
    fun setup() {
        TestViewModelScope.setupViewModelScope(CoroutineScope(Dispatchers.Unconfined))

        val events = mutableListOf<String>()
        val listener = object : ConfigViewModel.EventsListener {
            override fun routeToNews() {
                events.add("routeToNews")
            }
        }

        settings = MockSettings()
        listenerEvents = events
        viewModel = createConfigViewModel(
            settings = settings,
            eventsDispatcher = createTestEventsDispatcher(listener)
        )
    }

    @AfterTest
    fun tearDown() {
        TestViewModelScope.resetViewModelScope()
    }

    @Test
    fun `test default fields`() {
        assertEquals(
            expected = "ed155d0a445e4b4fbd878fe1f3bc1b7f",
            actual = viewModel.apiTokenField.value()
        )
        assertEquals(
            expected = "us",
            actual = viewModel.languageField.value()
        )
    }

    private fun createConfigViewModel(
        settings: Settings,
        eventsDispatcher: EventsDispatcher<ConfigViewModel.EventsListener>
    ): ConfigViewModel {
        val factory: SharedFactory = createSharedFactory(settings)
        return factory.configFactory.createConfigViewModel(eventsDispatcher)
    }
}
