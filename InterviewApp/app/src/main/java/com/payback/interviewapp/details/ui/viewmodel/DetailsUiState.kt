package com.payback.interviewapp.details.ui.viewmodel

internal sealed class DetailsUiState {
    data object Loading : DetailsUiState()

    data object Loaded : DetailsUiState()

    data class Error(val throwable: Throwable) : DetailsUiState()
}