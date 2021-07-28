/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */
import org.gradle.api.internal.artifacts.DefaultModuleVersionSelector

buildscript {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
    dependencies {
        classpath("dev.icerock.moko:resources-generator:0.16.2")
        classpath("dev.icerock.moko:network-generator:0.16.0")
        classpath("dev.icerock.moko:units-generator:0.6.1")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.5.20")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.35")
        classpath(":build-logic")
    }
}

allprojects {
    configurations.configureEach {
        resolutionStrategy {
            val coroutines: MinimalExternalModuleDependency = rootProject.libs.coroutines.get()
            val forcedCoroutines: ModuleVersionSelector = DefaultModuleVersionSelector.newSelector(
                coroutines.module,
                coroutines.versionConstraint.requiredVersion
            )
            force(forcedCoroutines)
        }
    }
}

tasks.register("clean", Delete::class).configure {
    group = "build"
    delete(rootProject.buildDir)
}
