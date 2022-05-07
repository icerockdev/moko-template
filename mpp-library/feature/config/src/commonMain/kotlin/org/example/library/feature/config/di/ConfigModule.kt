package org.example.library.feature.config.di

import org.example.library.feature.config.presentation.ConfigViewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

val DefaultTokenQualifier = StringQualifier("DefaultToken")
val DefaultLanguageQualifier = StringQualifier("DefaultLanguage")

fun configModule() = module {
    single { params ->
        ConfigViewModel(
            eventsDispatcher = params.get(),
            configStore = get(),
            validations = get(),
            defaultToken = get(DefaultTokenQualifier),
            defaultLanguage = get(DefaultLanguageQualifier)
        )
    }
}
