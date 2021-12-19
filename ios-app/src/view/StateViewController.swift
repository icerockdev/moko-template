//
//  StateViewController.swift
//  ios-app
//
//  Created by Aleksey Mikhailov on 19.12.2021.
//  Copyright © 2021 IceRock Development. All rights reserved.
//

import UIKit
import MultiPlatformLibrary

class StateViewController: UIViewController {
    @IBOutlet private var loginField: UITextField!
    @IBOutlet private var passwordField: UITextField!
    @IBOutlet private var submitButton: UIButton!
    
    private var viewModel: StateFlowViewModel!
    
    private var cancellation: (() -> KotlinUnit)? = nil
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        viewModel = StateFlowViewModel()

        cancellation = viewModel.isSubmitEnabledNative(
            { [weak self] data, r in
                print("set data \(data)")
                self?.submitButton.isEnabled = data.boolValue
                return r
            },
            { error, r in
                print("error caused! \(error)")
                return r
            }
        )
    }
    
    deinit {
        // clean viewmodel to stop all coroutines immediately
        viewModel.onCleared()
    }
}
