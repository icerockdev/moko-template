/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()

        jcenter {
            content {
                includeGroup("org.jetbrains.kotlinx")
                includeGroup("com.github.aakira")
            }
        }
    }
}

includeBuild("build-logic")

include(":android-app")
include(":mpp-library")
include(":mpp-library:domain")
include(":mpp-library:feature:config")
include(":mpp-library:feature:list")
