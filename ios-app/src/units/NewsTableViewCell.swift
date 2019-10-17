/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import UIKit
import MultiPlatformLibraryUnits

// Fillable interface from https://github.com/icerockdev/moko-units
class NewsTableViewCell: UITableViewCell, Fillable {
    typealias DataType = CellModel
    
    struct CellModel {
        let id: Int64
        let title: String
        let description: String
    }
    
    @IBOutlet private var titleLabel: UILabel!
    @IBOutlet private var descriptionLabel: UILabel!
    
    func fill(_ data: NewsTableViewCell.CellModel) {
        titleLabel.text = data.title
        descriptionLabel.text = data.description
    }
    
    func update(_ data: NewsTableViewCell.CellModel) {
        
    }
}

// Reusable interface from https://github.com/icerockdev/moko-units
extension NewsTableViewCell: Reusable {
    static func reusableIdentifier() -> String {
        return "NewsTableViewCell"
    }
    
    static func xibName() -> String {
        return "NewsTableViewCell"
    }
    
    static func bundle() -> Bundle {
        return Bundle.main
    }
}

