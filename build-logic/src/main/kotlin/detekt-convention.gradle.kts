/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("io.gitlab.arturbosch.detekt")
}

detekt {
    config.setFrom(rootProject.file("config/detekt.yml"))
    buildUponDefaultConfig = true
}

tasks.register("detektWithoutTests") {
    dependsOn(tasks.withType<Detekt>().matching { it.name.contains("Test").not() })
}

dependencies {
    "detektPlugins"("io.gitlab.arturbosch.detekt:detekt-formatting:1.19.0")
}
