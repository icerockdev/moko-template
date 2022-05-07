/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.app

import android.app.Application
import org.example.app.di.startAppKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startAppKoin(context = this)
    }
}
