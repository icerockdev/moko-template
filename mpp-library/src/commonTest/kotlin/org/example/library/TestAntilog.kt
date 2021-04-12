/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library

import com.github.aakira.napier.Antilog
import com.github.aakira.napier.Napier

internal class TestAntilog : Antilog() {
    override fun performLog(
        priority: Napier.Level,
        tag: String?,
        throwable: Throwable?,
        message: String?
    ) {
        buildString {
            append(priority.name)
            append(' ')

            if (tag != null) {
                append('[')
                append(tag)
                append("]: ")
            }
            if (message != null) {
                append(message)
            }
        }.let(::println)

        throwable?.printStackTrace()
    }
}
