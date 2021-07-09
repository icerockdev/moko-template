/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("com.android.library")
    id("android-base-convention")
    id("detekt-convention")
    id("org.jetbrains.kotlin.multiplatform")
    id("kotlin-parcelize")
    id("kotlinx-serialization")
    id("dev.icerock.mobile.multiplatform.android-manifest")
    id("dev.icerock.mobile.multiplatform-network-generator")
}

kotlin {
    android()
    ios()
}

dependencies {
    commonMainImplementation(libs.coroutines)
    commonMainImplementation(libs.kotlinSerialization)
    commonMainImplementation(libs.ktorClient)
    commonMainImplementation(libs.ktorClientLogging)

    commonMainImplementation(libs.mokoParcelize)
    commonMainImplementation(libs.mokoNetwork)

    commonMainImplementation(libs.multiplatformSettings)
    commonMainImplementation(libs.napier)
}

mokoNetwork {
    spec("news") {
        inputSpec = file("src/openapi.yml")
    }
}
