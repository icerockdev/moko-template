/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

object Versions {
    object Android {
        const val compileSdk = 28
        const val targetSdk = 28
        const val minSdk = 21
    }

    const val kotlin = "1.3.50"

    private const val mokoResources = "0.4.0"
    private const val mokoNetwork = "0.1.0"
    private const val mokoUnits = "0.1.1"

    object Plugins {
        const val kotlin = Versions.kotlin
        const val serialization = Versions.kotlin
        const val androidExtensions = Versions.kotlin
        const val mokoResources = Versions.mokoResources
        const val mokoNetwork = Versions.mokoNetwork
        const val mokoUnits = Versions.mokoUnits
    }

    object Libs {
        object Android {
            const val kotlinStdLib = Versions.kotlin
            const val appCompat = "1.0.2"
            const val material = "1.0.0"
            const val constraintLayout = "1.1.3"
            const val lifecycle = "2.0.0"
            const val recyclerView = "1.0.0"
        }

        object MultiPlatform {
            const val kotlinStdLib = Versions.kotlin

            const val coroutines = "1.3.0"
            const val serialization = "0.13.0"
            const val ktorClient = "1.2.4"
            const val ktorClientLogging = ktorClient

            const val mokoParcelize = "0.1.0"
            const val mokoTime = "0.1.0"
            const val mokoGraphics = "0.1.0"
            const val mokoMvvm = "0.3.0"
            const val mokoResources = Versions.mokoResources
            const val mokoNetwork = Versions.mokoNetwork
            const val mokoFields = "0.1.0"
            const val mokoPermissions = "0.1.0"
            const val mokoMedia = "0.1.0"
            const val mokoUnits = Versions.mokoUnits

            const val napier = "1.0.0"
            const val settings = "0.3.3"
        }
    }
}