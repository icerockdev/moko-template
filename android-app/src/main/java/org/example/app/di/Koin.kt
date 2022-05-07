package org.example.app.di

import android.content.Context
import org.example.library.startLibraryKoin
import org.koin.android.ext.koin.androidContext


fun startAppKoin(context: Context) =
    startLibraryKoin(antilogModule(), settingsModule(), unitsFactoryModule()).apply {
        androidContext(context)
    }
