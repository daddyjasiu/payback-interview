package com.payback.interviewapp.dashboard.ui.viewmodel

import com.payback.interviewapp.details.ui.mapper.UiDashboardItem

internal sealed class DashboardUiEvent {
    data class GoToDetailsScreen(val dashboardItem: UiDashboardItem) : DashboardUiEvent()
}