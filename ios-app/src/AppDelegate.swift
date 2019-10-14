/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import UIKit
import MultiPlatformLibrary

@UIApplicationMain
class AppDelegate: NSObject, UIApplicationDelegate {
    
    var window: UIWindow?
    
    static var factory: Factory__!
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil) -> Bool {
        AppDelegate.factory = Factory__(
            settings: AppleSettings(delegate: UserDefaults.standard),
            baseUrl: "https://newsapi.org/v2/",
            newsUnitsFactory: NewsListUnitsFactory()
        )
        return true
    }
}
