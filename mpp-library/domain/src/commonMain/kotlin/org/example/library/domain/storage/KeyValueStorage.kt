/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library.domain.storage

import com.russhwolf.settings.Settings
import com.russhwolf.settings.string

class KeyValueStorage(settings: Settings) {
    var token by settings.string("pref_token")
}
