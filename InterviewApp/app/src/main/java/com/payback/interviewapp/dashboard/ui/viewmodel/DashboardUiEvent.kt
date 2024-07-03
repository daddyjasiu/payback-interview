package com.payback.interviewapp.dashboard.ui.viewmodel

import com.payback.interviewapp.dashboard.ui.model.UiDashboardItem

internal sealed class DashboardUiEvent {
    data class GoToDetailsScreen(val dashboardItem: UiDashboardItem) : DashboardUiEvent()
    data class FetchItems(val tags: String) : DashboardUiEvent()
}