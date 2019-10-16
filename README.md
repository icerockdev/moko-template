![moko-template](img/logo.png)  
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

# Mobile Kotlin multiplatform project template
TODO

## Table of Contents
- [Features](#features)
- [Modules](#modules)
- [Screenshots](#screenshots)
- [Set Up Project](#set-up-project)
- [Contributing](#contributing)
- [License](#license)

## Features
- **Kotlin Multiplatform** - shared code on Kotlin for Android and iOS. *Don't Repeat Yourself*;
- **Kotlin gradle DSL** - project configuration in kotlin gradle dsl give more flexible way to configuration;
- **Modularized** - feature implemetation in separated modules;
- **Dependencies definition in buildSrc** - simplify dependency management across modules;
- **moko libraries** - all mobile kotlin libraries already have dependency definition. Common usecases already implemented - ViewModels, LiveData, resources management, runtime permissions access, media access, control lists content from shared code, network layer generation from OpenAPI;
- **Dependency Injection** - compile-time dependencies injection via `Factory` class of each module;
- **Parallel build of modules** - feature modules and `domain` module are independent of each other, this provides better caching of build results each module and ability to reuse module without changes.

## Modules
![modules scheme](https://user-images.githubusercontent.com/5010169/66910966-c7f47680-f039-11e9-8aa0-7485757cc39b.jpg)
Scheme show structure of project:  
- We have two applications:
  - `android-app` (writen on kotlin) - contains View layer (`Activity`, `Fragment`);
  - `ios-app` (writen on swift) - contains View layer (`UIViewController`, storyboards);
- Both applications depends on `mpp-library` (kotlin multiplatform) - contains `SharedFactory` which create instances of `ViewModel` for each screen;  
- `mpp-library` depends on:
  - `domain` (kotlin multiplatform) - contains domain entities, repositories, server api classes, DomainFactory which create instances all of them;
  - `feature:auth` (kotlin multiplatform) - contains auth feature ViewModel, store interface, AuthFactory which create instances of ViewModel;
  - `feature:news` (kotlin multiplatform) - contains news feature ViewModel, data source interface, list items factory interface, NewsFactory which create instances of ViewModel.

In `mpp-library` setting up connections between feature modules and `domain` module.

### Legend for schemes
![legend](https://user-images.githubusercontent.com/5010169/66910970-cd51c100-f039-11e9-9dfa-775a39b0d748.jpg)
Each color show in what module class/interface defined. Class and interface showed by different shapes.

### Auth module scheme
![auth module scheme](https://user-images.githubusercontent.com/5010169/66917408-84a10480-f047-11e9-8f4a-0c089e24ff6d.jpg)
Connections between `feature:auth` classes and `domain` classes implemented in `mpp-library` module.

### News module scheme
![news module scheme](https://user-images.githubusercontent.com/5010169/66917411-85d23180-f047-11e9-9e7f-b2f3387fac51.jpg)
Connections between `feature:news` classes and `domain` classes implemented in `mpp-library` module.  
`NewsUnitsFactory` interface implementation on both platforms - on android and ios.

## Screenshots
|Android|iOS|
|---|---|
|![android-app](https://user-images.githubusercontent.com/5010169/66911569-f9ba0d00-f03a-11e9-80b0-d4992bf61fbe.png)|![ios-app](https://user-images.githubusercontent.com/5010169/66911572-fa52a380-f03a-11e9-8425-19014e14c7b8.png)|
|![android-app](https://user-images.githubusercontent.com/5010169/66911571-fa52a380-f03a-11e9-9470-ea3dc05a6dff.png)|![ios-app](https://user-images.githubusercontent.com/5010169/66911574-fa52a380-f03a-11e9-9f67-a1a48865bf97.png)|

## Set Up Project
### Setup own ApplicationId
In `android-app/build.gradle.kts` change `org.example.app` to own appId at line:
```kotlin
applicationId = "org.example.app"
```
In ios-app change `Bundle Identifier` to own appId in Xcode project settings.

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
