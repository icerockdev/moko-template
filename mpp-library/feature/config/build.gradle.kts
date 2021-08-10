/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("multiplatform-library-convention")
}

dependencies {
    commonMainImplementation(libs.coroutines)

    androidMainImplementation(libs.lifecycleViewModel)

    commonMainImplementation(libs.mokoMvvmLiveData)
    commonMainImplementation(libs.mokoResources)
    commonMainImplementation(libs.mokoFields)
}
