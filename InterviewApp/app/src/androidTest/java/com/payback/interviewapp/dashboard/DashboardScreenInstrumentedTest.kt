package com.payback.interviewapp.dashboard

import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.payback.interviewapp.R
import com.payback.interviewapp.base.MainActivity
import com.payback.interviewapp.dashboard.ui.model.mockUiDashboardItem
import com.payback.interviewapp.dashboard.ui.view.DashboardAlertDialog
import com.payback.interviewapp.dashboard.ui.view.DashboardScreen
import com.payback.interviewapp.dashboard.ui.view.DashboardSearchBar
import com.payback.interviewapp.dashboard.ui.viewmodel.DashboardUiState
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DashboardScreenInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @After
    fun tearDown() {
        composeTestRule.activity.finish()
    }

    @Test
    fun testDashboardScreenErrorState() {
        composeTestRule.activity.setContent {
            DashboardScreen(
                uiState = DashboardUiState.Error(Exception("Test error")),
                onUiEvent = {}
            )
        }

        composeTestRule.onNodeWithText("Something went wrong.\nPlease try again.").assertExists()
    }

    @Test
    fun testDashboardScreenLoadedState() {
        val items = listOf(mockUiDashboardItem, mockUiDashboardItem, mockUiDashboardItem)
        composeTestRule.activity.setContent {
            DashboardScreen(
                uiState = DashboardUiState.Loaded(dashboardItems = items),
                onUiEvent = {}
            )
        }

        composeTestRule.onNodeWithText("Dashboard").assertExists()
    }

    @Test
    fun testDashboardSearchBar() {
        composeTestRule.activity.setContent {
            DashboardSearchBar(onSearch = {})
        }

        val searchHint = composeTestRule.activity.getString(R.string.dashboard_search_bar_hint)
        composeTestRule.onNodeWithText(searchHint).assertExists()
        val searchText = "flower"
        composeTestRule.onNodeWithText(searchHint).performTextInput(searchText)
        composeTestRule.onNodeWithText(searchHint).assertTextContains(searchText)
    }

    @Test
    fun testDashboardAlertDialog() {
        var dialogShown by mutableStateOf(true)
        composeTestRule.activity.setContent {
            if (dialogShown) {
                DashboardAlertDialog(
                    onConfirm = { dialogShown = false },
                    onDismiss = { dialogShown = false }
                )
            }
        }

        val confirmText =
            composeTestRule.activity.getString(R.string.dashboard_dialog_confirm_button)
        composeTestRule.onNodeWithText(confirmText).assertExists()
        composeTestRule.onNodeWithText(confirmText).performClick()
        assert(!dialogShown)
    }
}
