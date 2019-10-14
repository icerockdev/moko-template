/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import UIKit
import MultiPlatformLibrary
import MultiPlatformLibraryUnits

class NewsListUnitsFactory: NewsListViewModelUnitsFactory {
    func createNewsTile(
        id: Int64,
        title: String,
        description: StringDesc) -> UnitItem {
        return UITableViewCellUnit<NewsTableViewCell>(
            data: NewsTableViewCell.CellModel(
                id: id,
                title: title,
                description: description.localized()
            ),
            configurator: nil
        )
    }
}
