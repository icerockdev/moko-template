/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library.feature.auth.model

interface LoginStorage {
    var apiToken: String?
    var language: String?
}
