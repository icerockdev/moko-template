/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library.feature.auth.presentation

import dev.icerock.moko.fields.FormField
import dev.icerock.moko.fields.liveBlock
import dev.icerock.moko.fields.validate
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcherOwner
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.desc.StringDesc
import org.example.library.feature.auth.model.LoginStorage

class LoginViewModel(
    override val eventsDispatcher: EventsDispatcher<EventsListener>,
    private val loginStorage: LoginStorage,
    validations: Validations,
    defaultToken: String,
    defaultLanguage: String
) : ViewModel(), EventsDispatcherOwner<LoginViewModel.EventsListener> {

    val apiTokenField: FormField<String, StringDesc> =
        FormField(defaultToken, liveBlock(validations::validateToken))
    val languageField: FormField<String, StringDesc> =
        FormField(defaultLanguage, liveBlock(validations::validateLanguage))

    private val fields = listOf(apiTokenField, languageField)

    fun onSubmitPressed() {
        if (!fields.validate()) return

        loginStorage.apiToken = apiTokenField.value()
        loginStorage.language = languageField.value()

        eventsDispatcher.dispatchEvent { routeToNews() }
    }

    interface Validations {
        fun validateToken(value: String): StringDesc?
        fun validateLanguage(value: String): StringDesc?
    }

    interface EventsListener {
        fun routeToNews()
    }
}