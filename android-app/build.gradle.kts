/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("android-app-convention")
    id("detekt-convention")
    id("kotlin-kapt")
    id("dev.icerock.mobile.multiplatform-units")
    id("dagger.hilt.android.plugin")
}

android {
    buildFeatures.dataBinding = true
    buildFeatures.compose = true

    defaultConfig {
        applicationId = "org.example.app"
        minSdkVersion(21)
        versionCode = 1
        versionName = "0.1.0"

        val url = "https://newsapi.org/v2/"
        buildConfigField("String", "BASE_URL", "\"$url\"")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8 
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerVersion = "1.5.21"
        kotlinCompilerExtensionVersion = "1.0.1"
    }
}

kapt {
    javacOptions {
        // These options are normally set automatically via the Hilt Gradle plugin, but we
        // set them manually to workaround a bug in the Kotlin 1.5.20
        option("-Adagger.fastInit=ENABLED")
        option("-Adagger.hilt.android.internal.disableAndroidSuperclassValidation=true")
    }
}

dependencies {
    implementation(libs.appCompat)
    implementation(libs.material)
    implementation(libs.recyclerView)
    implementation(libs.swipeRefreshLayout)
    implementation(libs.mokoMvvmDataBinding)

    // Compose
    implementation(libs.composeActivity)
    implementation(libs.composeMaterial)
    implementation(libs.composeAnimation)
    implementation(libs.composeUi)
    implementation(libs.composeLifecycleViewmodel)
    androidTestImplementation(libs.composeTest)
    
    // Hilt
    implementation(libs.hilt)
    kapt(libs.hiltCompiler)

    implementation(projects.mppLibrary)
}

multiplatformUnits {
    classesPackage = "org.example.app"
    dataBindingPackage = "org.example.app"
    layoutsSourceSet = "main"
}
