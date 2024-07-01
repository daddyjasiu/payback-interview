package com.payback.interviewapp.dashboard.ui.viewmodel

import com.payback.interviewapp.dashboard.ui.mapper.UiDashboardItem

internal sealed class DashboardUiEvent {
    data class GoToDetailsScreen(val dashboardItem: UiDashboardItem) : DashboardUiEvent()
    data class FetchImages(val tags: String) : DashboardUiEvent()
}