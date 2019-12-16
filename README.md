![moko-template](https://user-images.githubusercontent.com/5010169/66987007-1bbe9880-f0ea-11e9-8c3c-46b25926794b.png)  
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0) ![kotlin-version](https://img.shields.io/badge/kotlin-1.3.61-orange)

# Mobile Kotlin multiplatform project template

A sample project that helps to start building a Mobile Kotlin Multiplatform application. It establishes an architecture optimized for building cross-platform mobile applications through separation of concerns between the UI and business logic.

## Table of Contents

- [Features](#features)
- [Modules](#modules)
- [Screenshots](#screenshots)
- [How to Run](#how-to-run)
- [Project setup](#project-setup)
- [Contributing](#contributing)
- [License](#license)

## Features

- **Kotlin Multiplatform**'s motto is *Don't Repeat Yourself*. Share the business logic code written in Kotlin between Android and iOS apps. 100% native UI and performance (shared code compiles into native libraries);
- **Kotlin Gradle DSL** – Configure project with flexible Kotlin Gradle DSL;
- **Modular-bazed architecture** – Implement app features independently of each other. Inject dependencies into features at compile-time through the use of the `Factory` class;
- **Parallel build of modules** – Feature modules and the `domain` module don't depend on each other. This provides caching of build artifacts for each module and results in better compilation time;
- **Dependencies definition in buildSrc** - Simplify dependency management across modules;
- **Ready to use** - Template project includes all [moko libraries](https://moko.icerock.dev) and supports most common use cases:
  - ViewModels;
  - LiveData;
  - Resource management;
  - Runtime permissions access;
  - Media access;
  - UI lists management from shared code;
  - Network layer generation from OpenAPI.

## Modules
### Legend
The color describes different modules and the shape - the type of an element (class|interface).
![legend](https://user-images.githubusercontent.com/5010169/66910970-cd51c100-f039-11e9-9dfa-775a39b0d748.jpg)

### Modules scheme
![modules scheme](https://user-images.githubusercontent.com/5010169/67163239-30629100-f375-11e9-9b10-fbcc3d3ea0bb.jpg)
This scheme shows the structure of the project:  

- We have two applications that represent the View application layer:
  - `android-app` written in Kotlin uses `Activity` and `Fragment`;
  - `ios-app` written in Swift uses `UIViewController` with Storyboards.
- Both applications depend on `mpp-library` (Kotlin Multiplatform) that provides access to `ViewModel`'s of each feature through `SharedFactory`. The library is responsible for setting up connections between the `feature` and `domain` modules.
- `mpp-library` consists of modules:
  - `domain` (Kotlin Multiplatform) - contains the domain entities, repositories, server API classes, and `DomainFactory` that creates instances for all of them;
  - `feature` (Kotlin Multiplatform). Every feature contains corresponding ViewModel, Factory, models, and interfaces it expects to be injected from the parent module. In this example:
    - `config` contains an config feature's ViewModel, data store interface and `ConfigFactory` that create instances of ViewModel;
    - `list` contains a items list feature's ViewModel, data source interface, list items factory interface, and `ListFactory` that create instances of ViewModel.

### Config module scheme
![config module scheme](https://user-images.githubusercontent.com/5010169/67163238-2e98cd80-f375-11e9-8747-1b50be5bdbfb.jpg)
The connections between the `feature:config` classes and the `domain` classes implemented in the `mpp-library` module.

### List module scheme
![list module scheme](https://user-images.githubusercontent.com/5010169/67163236-2e003700-f375-11e9-8c79-1b9ec96db26b.jpg)
The connections between the `feature:list` classes and the `domain` classes implemented in the `mpp-library` module.  
`SharedFactory.NewsUnitsFactory` interface is implemented on both platforms - Android (`android-app`) and iOS (`ios-app`).

## Screenshots

|Android|iOS|
|---|---|
|![android-app](https://user-images.githubusercontent.com/5010169/66911569-f9ba0d00-f03a-11e9-80b0-d4992bf61fbe.png)|![ios-app](https://user-images.githubusercontent.com/5010169/66911572-fa52a380-f03a-11e9-8425-19014e14c7b8.png)|
|![android-app](https://user-images.githubusercontent.com/5010169/66911571-fa52a380-f03a-11e9-9470-ea3dc05a6dff.png)|![ios-app](https://user-images.githubusercontent.com/5010169/66911574-fa52a380-f03a-11e9-9f67-a1a48865bf97.png)|

## How to Run

Android - just open repository root directory in Android Studio and press `Run`.  
iOS - run `pod install` in directory `ios-app`. Then open `ios-app/ios-app.xcworkspace` and press `Run` on simulator/device.

## Project setup

### Setup your own ApplicationId

*Just like in other native apps*  
In `android-app/build.gradle.kts` change `org.example.app` in the following line:
```kotlin
applicationId = "org.example.app"
```
In Xcode project settings change `Bundle Identifier`.

### Setup your own project name

*Just like in other native apps*  
In `android-app/src/main/res/values/strings.xml` change value of `app_name`.  
In Xcode project settings change `Display name`.

### Setup your own app icon

*Just like in other native apps*  
Put your android icon to `android-app/src/main/res` and setup usage in `android-app/src/main/AndroidManifest.xml`.  
Put your iOS icon to `ios-app/src/Assets.xcassets/AppIcon.appiconset`.

### Create new feature module

Create a file `mpp-library/feature/myfeature/build.gradle.kts` with the following content:
```kotlin
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("dev.icerock.mobile.multiplatform")
}

android {
    compileSdkVersion(Versions.Android.compileSdk)

    defaultConfig {
        minSdkVersion(Versions.Android.minSdk)
        targetSdkVersion(Versions.Android.targetSdk)
    }
}

dependencies {
    mppLibrary(Deps.Libs.MultiPlatform.kotlinStdLib)
}
```
Add module to `buildSrc/src/main/kotlin/Modules.kt`:
```kotlin
object Modules {
    object MultiPlatform {
        ...

        object Feature {
            ...

            val myfeature = MultiPlatformModule(
                name = ":mpp-library:feature:myfeature",
                exported = true
            )
        }
    }
}
```
Add module to `settings.gradle.kts`:
```kotlin
listOf(
    ...
    Modules.MultiPlatform.Feature.myfeature
).forEach { include(it.name) }
```
Add dependency to module from the `mpp-library` in `mpp-library/build.gradle.kts`:
```kotlin
val mppModules = listOf(
    ...
    Modules.MultiPlatform.Feature.myfeature
)
```

## Contributing

All development of template is performed in the `master` branch. Please send PRs with bug fixes to the `master` branch.

Please refer to the [contributing guide](CONTRIBUTING.md) for more details.

## License

    Copyright 2019 IceRock MAG Inc
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
