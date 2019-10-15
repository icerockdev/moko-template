/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("kotlin-android-extensions")
    id("kotlinx-serialization")
    id("dev.icerock.mobile.multiplatform")
    id("dev.icerock.mobile.multiplatform-network-generator")
}

android {
    compileSdkVersion(Versions.Android.compileSdk)

    defaultConfig {
        minSdkVersion(Versions.Android.minSdk)
        targetSdkVersion(Versions.Android.targetSdk)
    }
}

dependencies {
    mppLibrary(Deps.Libs.MultiPlatform.kotlinStdLib)
    mppLibrary(Deps.Libs.MultiPlatform.coroutines)
    mppLibrary(Deps.Libs.MultiPlatform.serialization)
    mppLibrary(Deps.Libs.MultiPlatform.ktorClient)
    mppLibrary(Deps.Libs.MultiPlatform.ktorClientLogging)

    mppLibrary(Deps.Libs.MultiPlatform.mokoParcelize)
    mppLibrary(Deps.Libs.MultiPlatform.mokoNetwork)

    mppLibrary(Deps.Libs.MultiPlatform.settings)
    mppLibrary(Deps.Libs.MultiPlatform.napier)
}

openApiGenerate {
    inputSpec.set(file("src/openapi.yml").path)
    generatorName.set("kotlin-ktor-client")
}
