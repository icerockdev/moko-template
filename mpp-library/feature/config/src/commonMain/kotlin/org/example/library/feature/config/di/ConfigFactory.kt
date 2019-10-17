/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library.feature.config.di

import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import org.example.library.feature.config.model.ConfigStore
import org.example.library.feature.config.presentation.ConfigViewModel

class ConfigFactory(
    private val configStore: ConfigStore,
    private val validations: ConfigViewModel.Validations,
    private val defaultToken: String,
    private val defaultLanguage: String
) {
    fun createConfigViewModel(
        eventsDispatcher: EventsDispatcher<ConfigViewModel.EventsListener>
    ) = ConfigViewModel(
        eventsDispatcher = eventsDispatcher,
        configStore = configStore,
        validations = validations,
        defaultToken = defaultToken,
        defaultLanguage = defaultLanguage
    )
}