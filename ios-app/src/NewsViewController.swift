/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import Foundation
import UIKit
import MultiPlatformLibrary
import MultiPlatformLibraryMvvm
import MultiPlatformLibraryUnits

class NewsViewController: UIViewController {
    @IBOutlet private var tableView: UITableView!
    @IBOutlet private var activityIndicator: UIActivityIndicatorView!
    
    private var viewModel: NewsListViewModel!
    private var dataSource: FlatUnitTableViewDataSource!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        viewModel = AppDelegate.factory.newsFactory.createNewsListViewModel()
        
        activityIndicator.bindVisibility(liveData: viewModel.state.isLoadingState())
        tableView.bindVisibility(liveData: viewModel.state.isSuccessState())
        
        dataSource = FlatUnitTableViewDataSource()
        dataSource.setup(for: tableView)
        
        viewModel.state.data().addObserver { [weak self] itemsObject in
            guard let items = itemsObject as? [UITableViewCellUnitProtocol] else { return }
            
            self?.dataSource.units = items
            self?.tableView.reloadData()
        }
    }
}
