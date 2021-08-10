/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("multiplatform-library-convention")
    id("kotlin-parcelize")
    id("kotlinx-serialization")
    id("dev.icerock.mobile.multiplatform-network-generator")
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
