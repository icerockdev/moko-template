/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("com.android.library")
    id("android-base-convention")
    id("detekt-convention")
    id("org.jetbrains.kotlin.multiplatform")
    id("kotlin-parcelize")
    id("dev.icerock.mobile.multiplatform.android-manifest")
}

kotlin {
    android()
    ios()
}

dependencies {
    commonMainImplementation(libs.coroutines)

    "androidMainImplementation"(libs.lifecycle)
    "androidMainImplementation"(libs.recyclerView)

    commonMainImplementation(libs.mokoMvvmLiveData)
    commonMainImplementation(libs.mokoMvvmState)
    commonMainImplementation(libs.mokoResources)
    commonMainImplementation(libs.mokoUnits)

    commonMainImplementation(libs.napier)
}
