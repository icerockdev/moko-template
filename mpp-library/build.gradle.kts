/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("multiplatform-library-convention")
    id("dev.icerock.mobile.multiplatform-resources")
    id("dev.icerock.mobile.multiplatform.ios-framework")
}

dependencies {
    commonMainImplementation(libs.coroutines)
    commonMainImplementation(libs.ktorClient)

    androidMainImplementation(libs.multidex)
    androidMainImplementation(libs.lifecycleViewModel)

    commonMainApi(libs.multiplatformSettings)
    commonMainApi(libs.napier)
    commonMainApi(libs.mokoParcelize)
    commonMainApi(libs.mokoResources)
    commonMainApi(libs.mokoMvvmCore)
    commonMainApi(libs.mokoMvvmLiveData)
    commonMainApi(libs.mokoMvvmState)
    commonMainApi(libs.mokoUnits)
    commonMainApi(libs.mokoFields)

    commonMainApi(projects.mppLibrary.domain)
    commonMainApi(projects.mppLibrary.feature.config)
    commonMainApi(projects.mppLibrary.feature.list)

    commonTestImplementation(libs.mokoTestCore)
    commonTestImplementation(libs.mokoMvvmTest)
    commonTestImplementation(libs.mokoUnitsTest)
    commonTestImplementation(libs.multiplatformSettingsTest)
    commonTestImplementation(libs.ktorClientMock)

}

multiplatformResources {
    multiplatformResourcesPackage = "org.example.library"
}

framework {
    export(projects.mppLibrary.domain)
    export(projects.mppLibrary.feature.config)
    export(projects.mppLibrary.feature.list)

    export(libs.multiplatformSettings)
    export(libs.napier)
    export(libs.mokoParcelize)
    export(libs.mokoResources)
    export(libs.mokoMvvmCore)
    export(libs.mokoMvvmLiveData)
    export(libs.mokoMvvmState)
    export(libs.mokoUnits)
    export(libs.mokoFields)
}
