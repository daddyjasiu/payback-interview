package com.payback.interviewapp.dashboard.ui.viewmodel

internal sealed class DashboardUiEvent {
    data class GoToDetailsScreen(val url: String) : DashboardUiEvent()
}