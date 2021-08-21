/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("io.gitlab.arturbosch.detekt")
}

detekt {
    source.setFrom(
        "src/commonMain/kotlin",
        "src/androidMain/kotlin",
        "src/iosMain/kotlin",
        "src/iosX64Main/kotlin",
        "src/iosArm64Main/kotlin",
        "src/main/kotlin",
        "src/main/java"
    )
}

dependencies {
    "detektPlugins"("io.gitlab.arturbosch.detekt:detekt-formatting:1.18.0")
}
