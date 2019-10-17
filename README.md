![moko-template](img/logo.png)  
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

# Mobile Kotlin multiplatform project template

## Table of Contents
- [Features](#features)
- [Modules](#modules)
- [Screenshots](#screenshots)
- [Project setup](#project-setup)
- [Contributing](#contributing)
- [License](#license)

## Features

- **Kotlin Multiplatform** - *Don't Repeat Yourself*. Share business logic code written in Kotlin with Android and iOS apps. 
- **Kotlin Gradle DSL** - Configure project with flexible Kotlin Gradle DSL.
- **Modularized architecture** - Implement app features independently of each other. Inject dependencies at compile-time into features through the use of `Factory` class.
- **Parallel build of modules** - Feature modules and `domain` module are independent of each other. This provides caching of build artifacts for each module and results in better compile time.
- **Dependencies definition in buildSrc** - Simplify dependency management across modules.
- **Ready to use** - Template project includes all [moko libraries](https://moko.icerock.dev) and demonstrates common use cases for:
  - ViewModels
  - LiveData
  - Resource management
  - Runtime permissions access
  - Media access 
  - UI lists management from shared code 
  - Network layer generation from OpenAPI

## Modules

![modules scheme](https://user-images.githubusercontent.com/5010169/66910966-c7f47680-f039-11e9-8aa0-7485757cc39b.jpg)
This scheme shows structure of the project:  

- We have two applications which represent View application layer:
  - `android-app` written in Kotlin uses `Activity` and `Fragment`
  - `ios-app` written in Swift uses `UIViewController` with Storyboards
- Both applications depend on `mpp-library` (kotlin multiplatform) which gives access to `ViewModel`'s of every feature through `SharedFactory`. It is responsible for setting up connections `feature` and `domain` modules
- `mpp-library` consists of modules:
  - `domain` (kotlin multiplatform) - contains domain entities, repositories, server api classes and `DomainFactory` which creates instances of them all
  - `feature` (kotlin multiplatform). Every feature contains corresponding ViewModel, Factory, models and interfaces it expects to be injected from the parent module. In this example:
    - `auth` contains auth feature ViewModel, data store interface and `AuthFactory` which create instances of ViewModel
    - `news` contains news feature ViewModel, data source interface, list items factory interface, NewsFactory which create instances of ViewModel.

### Legend for schemes

Color describes different modules. Shape - type of an element (class|interface)

![legend](https://user-images.githubusercontent.com/5010169/66910970-cd51c100-f039-11e9-9dfa-775a39b0d748.jpg)

### Auth module scheme

![auth module scheme](https://user-images.githubusercontent.com/5010169/66917408-84a10480-f047-11e9-8f4a-0c089e24ff6d.jpg)
Connections between `feature:auth` classes and `domain` classes implemented in `mpp-library` module.

### News module scheme
![news module scheme](https://user-images.githubusercontent.com/5010169/66917411-85d23180-f047-11e9-9e7f-b2f3387fac51.jpg)
Connections between `feature:news` classes and `domain` classes implemented in `mpp-library` module.  
`NewsListViewModel.UnitsFactory` interface is implemented on both platforms - Android (`android-app`) and iOS (ios-app).

## Screenshots

|Android|iOS|
|---|---|
|![android-app](https://user-images.githubusercontent.com/5010169/66911569-f9ba0d00-f03a-11e9-80b0-d4992bf61fbe.png)|![ios-app](https://user-images.githubusercontent.com/5010169/66911572-fa52a380-f03a-11e9-8425-19014e14c7b8.png)|
|![android-app](https://user-images.githubusercontent.com/5010169/66911571-fa52a380-f03a-11e9-9470-ea3dc05a6dff.png)|![ios-app](https://user-images.githubusercontent.com/5010169/66911574-fa52a380-f03a-11e9-9f67-a1a48865bf97.png)|

## Project setup

### Setup your own ApplicationId

In `android-app/build.gradle.kts` change `org.example.app` at line:
```kotlin
applicationId = "org.example.app"
```
In ios-app change `Bundle Identifier` in Xcode project settings.

## Contributing

All development (both new features and bug fixes) is performed in `develop` branch. This way `master` sources always contain sources of the most recently released version. Please send PRs with bug fixes to `develop` branch. Fixes to documentation in markdown files are an exception to this rule. They are updated directly in `master`.

The `develop` branch is pushed to `master` during release.

More detailed guide for contributers see in [contributing guide](CONTRIBUTING.md).

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
