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
    @IBOutlet private var emptyView: UIView!
    @IBOutlet private var errorView: UIView!
    @IBOutlet private var errorLabel: UILabel!
    
    private var viewModel: NewsListViewModel!
    private var dataSource: FlatUnitTableViewDataSource!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        viewModel = AppDelegate.factory.newsFactory.createNewsListViewModel()
        
        activityIndicator.bindVisibility(liveData: viewModel.state.isLoadingState())
        tableView.bindVisibility(liveData: viewModel.state.isSuccessState())
        emptyView.bindVisibility(liveData: viewModel.state.isEmptyState())
        errorView.bindVisibility(liveData: viewModel.state.isErrorState())
        
        let errorText: LiveData<StringDesc> = viewModel.state.error().map { $0 as? StringDesc } as! LiveData<StringDesc>
        errorLabel.bindText(liveData: errorText)
        
        dataSource = FlatUnitTableViewDataSource()
        dataSource.setup(for: tableView)
        
        viewModel.state.data().addObserver { [weak self] itemsObject in
            guard let items = itemsObject as? [UITableViewCellUnitProtocol] else { return }
            
            self?.dataSource.units = items
            self?.tableView.reloadData()
        }
    }
    
    @IBAction func onRetryPressed() {
        viewModel.onRetryPressed()
    }
}
