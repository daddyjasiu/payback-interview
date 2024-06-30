package com.payback.interviewapp.dashboard.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.payback.interviewapp.R
import com.payback.interviewapp.base.ui.BaseScreen
import com.payback.interviewapp.dashboard.ui.viewmodel.DashboardUiEvent
import com.payback.interviewapp.dashboard.ui.viewmodel.DashboardUiState

@Composable
internal fun DashboardScreen(uiState: DashboardUiState, onUiEvent: (DashboardUiEvent) -> Unit) {
    when (uiState) {
        is DashboardUiState.Loading -> {
            CircularProgressIndicator()
        }

        is DashboardUiState.Loaded -> {
            BaseScreen(
                title = stringResource(R.string.dashboard_title),
            ) { innerPadding ->
                Column(modifier = Modifier.padding(innerPadding)) {
                    Button(onClick = { onUiEvent(DashboardUiEvent.GoToDetailsScreen("jd")) }) {

                    }
                }
            }
        }

        is DashboardUiState.Error -> {}
    }

}
