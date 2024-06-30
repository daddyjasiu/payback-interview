package com.payback.interviewapp.dashboard.ui.viewmodel

internal sealed class DashboardUiState {
    data object Loading : DashboardUiState()

    data class Loaded(
        val url: String,
    ) : DashboardUiState()

    data class Error(val throwable: Throwable) : DashboardUiState()
}