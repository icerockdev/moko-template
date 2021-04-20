/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.mvvm.test

import dev.icerock.moko.test.cases.TestCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class TestViewModelScopeRule : TestCases.Rule {
    lateinit var coroutineScope: CoroutineScope

    override fun setup() {
        coroutineScope = CoroutineScope(Dispatchers.Unconfined)
        TestViewModelScope.setupViewModelScope(coroutineScope)
    }

    override fun tearDown() {
        TestViewModelScope.resetViewModelScope()
    }
}
