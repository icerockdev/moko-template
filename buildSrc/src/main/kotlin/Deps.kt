/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */


object Deps {
    private const val materialVersion = "1.2.1"
    private const val recyclerViewVersion = "1.1.0"
    private const val swipeRefreshLayoutVersion = "1.1.0"
    private const val constraintLayoutVersion = "2.0.0"
    private const val lifecycleVersion = "2.2.0"
    private const val glideVersion = "4.9.0"
    private const val androidAppCompatVersion = "1.1.0"
    private const val espressoCoreVersion = "3.2.0"
    private const val testRunnerVersion = "1.2.0"
    private const val testExtJunitVersion = "1.1.1"

    // should be as kotlin version! see buildSrc/build.gradle.kts
    private const val kotlinTestVersion = "1.4.21"
    // should be as kotlin version! see buildSrc/build.gradle.kts
    private const val kotlinxSerializationPluginVersion = "1.4.21"
    private const val kotlinxSerializationVersion = "1.0.0-RC"
    private const val kotlinxDateTimeVersion = "0.1.0"
    private const val coroutinesVersion = "1.4.2-native-mt"
    private const val ktorClientVersion = "1.4.0"

    private const val detektVersion = "1.12.0"

    private const val mokoGraphicsVersion = "0.5.0"
    private const val mokoParcelizeVersion = "0.5.0"
    private const val mokoResourcesVersion = "0.13.2"
    private const val mokoMvvmVersion = "0.8.1"
    private const val mokoErrorsVersion = "0.3.0"
    private const val mokoNetworkVersion = "0.8.0"
    private const val mokoUnitsVersion = "0.4.1"
    private const val mokoPermissionsVersion = "0.6.0"
    private const val mokoMediaVersion = "0.5.0"
    private const val mokoFieldsVersion = "0.5.0"

    private const val multiplatformSettingsVersion = "0.6.1"
    private const val napierVersion = "1.4.1"

    object Android {
        const val compileSdk = 30
        const val targetSdk = 30
        const val minSdk = 16
    }

    object Plugins {
        val androidApplication = GradlePlugin(id = "com.android.application")
        val androidLibrary = GradlePlugin(id = "com.android.library")
        val kotlinMultiplatform = GradlePlugin(id = "org.jetbrains.kotlin.multiplatform")
        val kotlinKapt = GradlePlugin(id = "kotlin-kapt")
        val kotlinAndroid = GradlePlugin(id = "kotlin-android")
        val kotlinAndroidExtensions = GradlePlugin(id = "kotlin-android-extensions")
        val kotlinSerialization = GradlePlugin(
            id = "org.jetbrains.kotlin.plugin.serialization",
            module = "org.jetbrains.kotlin:kotlin-serialization:$kotlinxSerializationPluginVersion"
        )

        val mobileMultiplatform = GradlePlugin(id = "dev.icerock.mobile.multiplatform")
        val iosFramework = GradlePlugin(id = "dev.icerock.mobile.multiplatform.ios-framework")

        val mokoNetwork = GradlePlugin(
            id = "dev.icerock.mobile.multiplatform-network-generator",
            module = "dev.icerock.moko:network-generator:$mokoNetworkVersion"
        )
        val mokoResources = GradlePlugin(
            id = "dev.icerock.mobile.multiplatform-resources",
            module = "dev.icerock.moko:resources-generator:$mokoResourcesVersion"
        )
        val mokoUnits = GradlePlugin(
            id = "dev.icerock.mobile.multiplatform-units",
            module = "dev.icerock.moko:units-generator:$mokoUnitsVersion"
        )

        val detekt = GradlePlugin(
            id = "io.gitlab.arturbosch.detekt",
            version = detektVersion
        )
    }

    object Libs {
        object Android {
            const val appCompat =
                "androidx.appcompat:appcompat:$androidAppCompatVersion"
            const val material =
                "com.google.android.material:material:$materialVersion"
            const val recyclerView =
                "androidx.recyclerview:recyclerview:$recyclerViewVersion"
            const val swipeRefreshLayout =
                "androidx.swiperefreshlayout:swiperefreshlayout:$swipeRefreshLayoutVersion"
            const val constraintLayout =
                "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
            val glide =
                "com.github.bumptech.glide:glide:$glideVersion"
            val lifecycle =
                "androidx.lifecycle:lifecycle-extensions:$lifecycleVersion"
            val ktorClientOkHttp =
                "io.ktor:ktor-client-okhttp:$ktorClientVersion"

            object Tests {
                const val espressoCore =
                    "androidx.test.espresso:espresso-core:$espressoCoreVersion"
                const val kotlinTestJUnit =
                    "org.jetbrains.kotlin:kotlin-test-junit:$kotlinTestVersion"
                const val testCore =
                    "androidx.test:core:1.3.0"
                const val robolectric =
                    "org.robolectric:robolectric:4.3"
                const val testRunner =
                    "androidx.test:runner:$testRunnerVersion"
                const val testRules =
                    "androidx.test:rules:$testRunnerVersion"
                const val testExtJunit =
                    "androidx.test.ext:junit:$testExtJunitVersion"
            }
        }

        object MultiPlatform {
            const val kotlinSerialization =
                "org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinxSerializationVersion"
            const val kotlinxDateTime =
                "org.jetbrains.kotlinx:kotlinx-datetime:$kotlinxDateTimeVersion"
            const val coroutines =
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
            const val ktorClient =
                "io.ktor:ktor-client-core:$ktorClientVersion"
            const val ktorClientLogging =
                "io.ktor:ktor-client-logging:$ktorClientVersion"
            val mokoResources = "dev.icerock.moko:resources:$mokoResourcesVersion"
                .defaultMPL(ios = true)
            val mokoParcelize = "dev.icerock.moko:parcelize:$mokoParcelizeVersion"
                .defaultMPL(ios = true)
            val mokoGraphics = "dev.icerock.moko:graphics:$mokoGraphicsVersion"
                .defaultMPL(ios = true)
            val mokoMvvm = "dev.icerock.moko:mvvm:$mokoMvvmVersion"
                .defaultMPL(ios = true)
            val mokoErrors = "dev.icerock.moko:errors:$mokoErrorsVersion"
                .defaultMPL(ios = true)
            val mokoNetwork = "dev.icerock.moko:network:$mokoNetworkVersion"
                .defaultMPL(ios = true)
            val mokoNetworkErrors = "dev.icerock.moko:network-errors:$mokoNetworkVersion"
                .defaultMPL(ios = true)
            val mokoPermissions = "dev.icerock.moko:permissions:$mokoPermissionsVersion"
                .defaultMPL(ios = true)
            val mokoMedia = "dev.icerock.moko:media:$mokoMediaVersion"
                .defaultMPL(ios = true)
            val mokoUnits = "dev.icerock.moko:units:$mokoUnitsVersion"
                .defaultMPL(ios = true)
            val mokoFields = "dev.icerock.moko:fields:$mokoFieldsVersion"
                .defaultMPL(ios = true)
            val multiplatformSettings =
                "com.russhwolf:multiplatform-settings:$multiplatformSettingsVersion"
                .defaultMPL(ios = true)
            val napier = "com.github.aakira:napier:$napierVersion"
                .defaultMPL(ios = true)

            object Tests {
                const val kotlinTest =
                    "org.jetbrains.kotlin:kotlin-test-common:$kotlinTestVersion"
                const val kotlinTestAnnotations =
                    "org.jetbrains.kotlin:kotlin-test-annotations-common:$kotlinTestVersion"
            }
        }

        object Detekt {
            const val detektFormatting =
                "io.gitlab.arturbosch.detekt:detekt-formatting:$detektVersion"
        }
    }

    object Modules {
        val domain = MultiPlatformModule(
            name = ":mpp-library:domain",
            exported = true
        )

        object Feature {
            val config = MultiPlatformModule(
                name = ":mpp-library:feature:config",
                exported = true
            )
            val list = MultiPlatformModule(
                name = ":mpp-library:feature:list",
                exported = true
            )
        }
    }
}
