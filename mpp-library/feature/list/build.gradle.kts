/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("multiplatform-library-convention")
    id("kotlin-parcelize")
}

dependencies {
    commonMainImplementation(libs.coroutines)

    androidMainImplementation(libs.lifecycleViewModel)
    androidMainImplementation(libs.recyclerView)

    commonMainImplementation(libs.mokoMvvmLiveData)
    commonMainImplementation(libs.mokoMvvmState)
    commonMainImplementation(libs.mokoResources)
    commonMainImplementation(libs.mokoUnits)

    commonMainImplementation(libs.napier)
}
