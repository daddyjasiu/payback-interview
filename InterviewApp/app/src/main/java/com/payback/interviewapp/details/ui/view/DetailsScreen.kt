package com.payback.interviewapp.details.ui.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.payback.interviewapp.R
import com.payback.interviewapp.base.ui.BaseScreen
import com.payback.interviewapp.details.ui.viewmodel.DetailsUiEvent
import com.payback.interviewapp.details.ui.viewmodel.DetailsUiState

@Composable
internal fun DetailsScreen(uiState: DetailsUiState, onUiEvent: (DetailsUiEvent) -> Unit) {
    BaseScreen(
        title = stringResource(R.string.details_title),
        onBackButtonPressed = { onUiEvent(DetailsUiEvent.GoBack) },
    ) {
        Text(text = "asdasd")
    }
}