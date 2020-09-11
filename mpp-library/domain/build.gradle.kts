/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    plugin(Deps.Plugins.androidLibrary)
    plugin(Deps.Plugins.kotlinMultiplatform)
    plugin(Deps.Plugins.kotlinAndroidExtensions)
    plugin(Deps.Plugins.kotlinSerialization)
    plugin(Deps.Plugins.mobileMultiplatform)
    plugin(Deps.Plugins.mokoNetwork)
}

dependencies {
    commonMainImplementation(Deps.Libs.MultiPlatform.coroutines)
    commonMainImplementation(Deps.Libs.MultiPlatform.kotlinSerialization)
    commonMainImplementation(Deps.Libs.MultiPlatform.ktorClient)
    commonMainImplementation(Deps.Libs.MultiPlatform.ktorClientLogging)

    commonMainImplementation(Deps.Libs.MultiPlatform.mokoParcelize.common)
    commonMainImplementation(Deps.Libs.MultiPlatform.mokoNetwork.common)

    commonMainImplementation(Deps.Libs.MultiPlatform.multiplatformSettings.common)
    commonMainImplementation(Deps.Libs.MultiPlatform.napier.common)
}

openApiGenerate {
    inputSpec.set(file("src/openapi.yml").path)
    generatorName.set("kotlin-ktor-client")
}
