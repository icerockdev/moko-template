/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library.feature.config

import com.russhwolf.settings.MockSettings
import com.russhwolf.settings.Settings
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.test.TestViewModelScopeRule
import dev.icerock.moko.mvvm.test.createTestEventsDispatcher
import dev.icerock.moko.test.cases.InstantTaskRule
import dev.icerock.moko.test.cases.TestCases
import org.example.library.feature.config.presentation.ConfigViewModel
import org.example.library.startTestKoin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.KoinTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ConfigViewModelTests : TestCases()  {
    override val rules: List<Rule> = listOf(
        InstantTaskRule(),
        TestViewModelScopeRule()
    )

    private lateinit var settings: MockSettings
    private lateinit var listener: ConfigViewModel.EventsListener
    private lateinit var listenerEvents: List<String>
    private lateinit var koinApp: KoinApplication

    @BeforeTest
    fun setup() {
        val events = mutableListOf<String>()
        listener = object : ConfigViewModel.EventsListener {
            override fun routeToNews() {
                events.add("routeToNews")
            }
        }
        settings = MockSettings()
        listenerEvents = events
        koinApp = startTestKoin(settings)
    }

    @AfterTest
    fun cleanup() {
        stopKoin()
    }

    @Test
    fun `test default fields`() {
        val viewModel = createConfigViewModel(createTestEventsDispatcher(listener))

        assertEquals(
            expected = "ed155d0a445e4b4fbd878fe1f3bc1b7f",
            actual = viewModel.apiTokenField.value()
        )
        assertEquals(
            expected = "us",
            actual = viewModel.languageField.value()
        )
    }

    @Test
    fun `test saved fields`() {
        settings.putString("pref_token", "test")
        settings.putString("pref_language", "ru")

        val viewModel = createConfigViewModel(createTestEventsDispatcher(listener))

        assertEquals(
            expected = "test",
            actual = viewModel.apiTokenField.value()
        )
        assertEquals(
            expected = "ru",
            actual = viewModel.languageField.value()
        )
    }

    private fun createConfigViewModel(eventsDispatcher: EventsDispatcher<ConfigViewModel.EventsListener>): ConfigViewModel =
        koinApp.koin.get { parametersOf(eventsDispatcher) }
}
