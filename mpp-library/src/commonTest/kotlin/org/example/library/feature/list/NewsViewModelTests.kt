/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.library.feature.list

import com.russhwolf.settings.MockSettings
import com.russhwolf.settings.Settings
import dev.icerock.moko.mvvm.ResourceState
import dev.icerock.moko.mvvm.test.TestViewModelScopeRule
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.test.cases.InstantTaskRule
import dev.icerock.moko.test.cases.TestCases
import dev.icerock.moko.test.waitChildrenCompletion
import dev.icerock.moko.units.TableUnitItem
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import org.example.library.*
import org.example.library.domain.entity.News
import org.example.library.feature.list.presentation.ListViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NewsViewModelTests : TestCases() {
    private val testViewModelScopeRule = TestViewModelScopeRule()

    override val rules: List<Rule> = listOf(
        InstantTaskRule(),
        testViewModelScopeRule
    )

    private lateinit var settings: MockSettings
    private lateinit var koinApp: KoinApplication

    @BeforeTest
    fun setup() {
        settings = MockSettings()
        koinApp = startTestKoin(settings)
    }

    @AfterTest
    fun cleanup() {
        stopKoin()
    }

    @Test
    fun `load news items`() {
        val viewModel = createViewModel()

        assertTrue(viewModel.state.value is ResourceState.Empty)

        viewModel.onCreated()

        testViewModelScopeRule.coroutineScope.waitChildrenCompletion()

        val dataState = viewModel.state.value
        assertTrue(dataState is ResourceState.Success, "state not data - $dataState")
        val items = listOf<TableUnitItem>(
            NewsTableUnit(
                id = "https://nypost.com/2021/04/16/trump-takes-jab-at-bidens-lunacy-of-jj-vaccination-pause/".hashCode()
                    .toLong(),
                title = "Trump takes jab at 'lunacy' of Johnson & Johnson vaccination pause - New York Post ",
                description = "Former President Donald Trump used a New York Post opinion piece to take aim at the Biden administration on Friday evening for allowing for a pause in the Johnson & Johnson coronavirus vaccine,…".desc()
            ),
            NewsTableUnit(
                id = "https://www.cnbc.com/2021/04/16/cramer-lightning-round-i-would-buy-lithia-motors-right-here.html".hashCode()
                    .toLong(),
                title = "Cramer's lightning round: I'd buy Lithia Motors right here - CNBC",
                description = "\"Mad Money\" host Jim Cramer rings the lightning round bell, which means he's giving his answers to callers' stock questions at rapid speed.".desc()
            )
        )
        assertEquals(expected = items, actual = dataState.data)
    }

    private fun createViewModel(): ListViewModel<News> =
        koinApp.koin.get()
}
