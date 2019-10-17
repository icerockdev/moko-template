/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import Foundation
import UIKit
import MultiPlatformLibrary
import MultiPlatformLibraryMvvm
import SkyFloatingLabelTextField

class ConfigViewController: UIViewController {
    @IBOutlet private var tokenField: SkyFloatingLabelTextField!
    @IBOutlet private var languageField: SkyFloatingLabelTextField!
    
    private var viewModel: ConfigViewModel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        viewModel = AppComponent.factory.configFactory.createConfigViewModel(eventsDispatcher: EventsDispatcher(listener: self))
        
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

extension ConfigViewController: ConfigViewModelEventsListener {
    func routeToNews() {
        performSegue(withIdentifier: "routeToNews", sender: nil)
    }
}
