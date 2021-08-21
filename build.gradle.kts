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
        classpath(libs.mokoResourcesGeneratorGradle)
        classpath(libs.mokoNetworkGeneratorGradle)
        classpath(libs.mokoUnitsGeneratorGradle)
        classpath(libs.kotlinSerializationGradle)
        classpath(libs.hiltGradle)
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
