package com.payback.interviewapp.details.ui.viewmodel

internal sealed class DetailsUiEvent {
    data object GoBack : DetailsUiEvent()
}