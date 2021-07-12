/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}
