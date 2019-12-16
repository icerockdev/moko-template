/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import UIKit
import MultiPlatformLibrary
import MultiPlatformLibraryUnits

class NewsListUnitsFactory: SharedFactoryNewsUnitsFactory {
    func createNewsTile(
        id: Int64,
        title: String,
        description: StringDesc) -> TableUnitItem {
        // create unit for https://github.com/icerockdev/moko-units
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
