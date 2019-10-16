/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library.feature.auth.di

import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import org.example.library.feature.auth.model.AuthStore
import org.example.library.feature.auth.presentation.AuthViewModel

class AuthFactory(
    private val authStore: AuthStore,
    private val validations: AuthViewModel.Validations,
    private val defaultToken: String,
    private val defaultLanguage: String
) {
    fun createAuthViewModel(
        eventsDispatcher: EventsDispatcher<AuthViewModel.EventsListener>
    ) = AuthViewModel(
        eventsDispatcher = eventsDispatcher,
        authStore = authStore,
        validations = validations,
        defaultToken = defaultToken,
        defaultLanguage = defaultLanguage
    )
}