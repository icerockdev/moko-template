/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    plugin(Deps.Plugins.androidLibrary)
    plugin(Deps.Plugins.kotlinMultiplatform)
    plugin(Deps.Plugins.kotlinAndroidExtensions)
    plugin(Deps.Plugins.mobileMultiplatform)
}

dependencies {
    commonMainImplementation(Deps.Libs.MultiPlatform.coroutines)

    androidMainImplementation(Deps.Libs.Android.lifecycle)
    androidMainImplementation(Deps.Libs.Android.recyclerView)

    commonMainImplementation(Deps.Libs.MultiPlatform.mokoMvvm.common)
    commonMainImplementation(Deps.Libs.MultiPlatform.mokoResources.common)
    commonMainImplementation(Deps.Libs.MultiPlatform.mokoUnits.common)

    commonMainImplementation(Deps.Libs.MultiPlatform.napier.common)
}
