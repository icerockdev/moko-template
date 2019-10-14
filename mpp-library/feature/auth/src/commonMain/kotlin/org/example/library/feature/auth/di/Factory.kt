/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library.feature.auth.di

import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import org.example.library.feature.auth.model.LoginStorage
import org.example.library.feature.auth.presentation.LoginViewModel

class Factory(
    private val loginStorage: LoginStorage,
    private val loginValidations: LoginViewModel.Validations,
    private val defaultToken: String,
    private val defaultLanguage: String
) {
    fun createLoginViewModel(
        eventsDispatcher: EventsDispatcher<LoginViewModel.EventsListener>
    ) = LoginViewModel(
        eventsDispatcher = eventsDispatcher,
        loginStorage = loginStorage,
        validations = loginValidations,
        defaultToken = defaultToken,
        defaultLanguage = defaultLanguage
    )
}