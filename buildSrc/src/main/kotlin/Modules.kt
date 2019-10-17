/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

object Modules {
    object MultiPlatform {
        val domain = MultiPlatformModule(
            name = ":mpp-library:domain",
            exported = true
        )

        object Feature {
            val config = MultiPlatformModule(
                name = ":mpp-library:feature:config",
                exported = true
            )
            val news = MultiPlatformModule(
                name = ":mpp-library:feature:news",
                exported = true
            )
        }
    }
}