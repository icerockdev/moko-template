/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import Foundation
import UIKit
import MultiPlatformLibrary
import MultiPlatformLibraryMvvm
import SkyFloatingLabelTextField

class AuthViewController: UIViewController {
    @IBOutlet private var tokenField: SkyFloatingLabelTextField!
    @IBOutlet private var languageField: SkyFloatingLabelTextField!
    
    private var viewModel: LoginViewModel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        viewModel = AppDelegate.factory.authFactory.createLoginViewModel(eventsDispatcher: EventsDispatcher(listener: self))
        
        tokenField.bindTextTwoWay(liveData: viewModel.apiTokenField.data)
        tokenField.bindError(liveData: viewModel.apiTokenField.error)
        
        languageField.bindTextTwoWay(liveData: viewModel.languageField.data)
        languageField.bindError(liveData: viewModel.languageField.error)
    }
    
    @IBAction func onSubmitPressed() {
        viewModel.onSubmitPressed()
    }
    
    deinit {
        viewModel.onCleared()
    }
}

extension AuthViewController: LoginViewModelEventsListener {
    func routeToNews() {
        performSegue(withIdentifier: "routeToNews", sender: nil)
    }
}
