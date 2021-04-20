/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    plugin(Deps.Plugins.androidLibrary)
    plugin(Deps.Plugins.kotlinMultiplatform)
    plugin(Deps.Plugins.mobileMultiplatform)
    plugin(Deps.Plugins.mokoResources)
    plugin(Deps.Plugins.iosFramework)
}

val mppLibs = listOf(
    Deps.Libs.MultiPlatform.multiplatformSettings,
    Deps.Libs.MultiPlatform.napier,
    Deps.Libs.MultiPlatform.mokoParcelize,
    Deps.Libs.MultiPlatform.mokoResources,
    Deps.Libs.MultiPlatform.mokoMvvmCore,
    Deps.Libs.MultiPlatform.mokoMvvmLiveData,
    Deps.Libs.MultiPlatform.mokoMvvmState,
    Deps.Libs.MultiPlatform.mokoUnits,
    Deps.Libs.MultiPlatform.mokoFields
)
val mppModules = listOf(
    Deps.Modules.domain,
    Deps.Modules.Feature.config,
    Deps.Modules.Feature.list
)

dependencies {
    commonMainImplementation(Deps.Libs.MultiPlatform.coroutines)
    commonMainImplementation(Deps.Libs.MultiPlatform.ktorClient)

    androidMainImplementation(Deps.Libs.Android.lifecycle)

    mppLibs.forEach { commonMainApi(it.common) }
    mppModules.forEach { commonMainApi(project(it.name)) }

    commonTestImplementation(Deps.Libs.MultiPlatform.Tests.mokoTestCore)
    commonTestImplementation(Deps.Libs.MultiPlatform.Tests.mokoMvvmTest)
    commonTestImplementation(Deps.Libs.MultiPlatform.Tests.mokoUnitsTest)
    commonTestImplementation(Deps.Libs.MultiPlatform.Tests.multiplatformSettingsTest)
    commonTestImplementation(Deps.Libs.MultiPlatform.Tests.ktorClientMock)
}

multiplatformResources {
    multiplatformResourcesPackage = "org.example.library"
}

framework {
    mppModules.forEach { export(it) }
    mppLibs.forEach { export(it) }
}
