package com.payback.interviewapp.dashboard.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
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
                LazyVerticalGrid(
                    modifier = Modifier.padding(innerPadding),
                    columns = GridCells.Fixed(2)
                ) {
                    items(uiState.dashboardItems) { item ->
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    onUiEvent(DashboardUiEvent.GoToDetailsScreen(item))
                                }
                        ) {
                            Column {
                                Image(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(88.dp),
                                    painter = rememberAsyncImagePainter(item.url),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                                Text(
                                    text = item.username,
                                    modifier = Modifier.padding(8.dp)
                                )
                                Text(
                                    text = item.tags.joinToString(),
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        is DashboardUiState.Error -> {}
    }
}
