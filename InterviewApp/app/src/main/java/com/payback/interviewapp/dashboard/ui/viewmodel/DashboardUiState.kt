package com.payback.interviewapp.dashboard.ui.viewmodel

import com.payback.interviewapp.dashboard.ui.model.UiDashboardItem

internal sealed class DashboardUiState {
    data object Loading : DashboardUiState()

    data class Loaded(
        val dashboardItems: List<UiDashboardItem>
    ) : DashboardUiState()

    data class Error(val throwable: Throwable) : DashboardUiState()
}