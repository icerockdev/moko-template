/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library.domain.repository

import org.example.library.domain.storage.KeyValueStorage

class ConfigRepository(
    private val keyValueStorage: KeyValueStorage
) {
    var apiToken: String?
        get() = keyValueStorage.token
        set(value) {
            keyValueStorage.token = value
        }

    var language: String?
        get() = keyValueStorage.language
        set(value) {
            keyValueStorage.language = value
        }
}
