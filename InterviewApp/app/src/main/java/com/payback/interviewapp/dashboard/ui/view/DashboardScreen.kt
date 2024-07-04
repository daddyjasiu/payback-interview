package com.payback.interviewapp.dashboard.ui.view

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.payback.interviewapp.R
import com.payback.interviewapp.base.theme.InterviewAppTheme
import com.payback.interviewapp.base.ui.BaseScreen
import com.payback.interviewapp.base.ui.Emojis
import com.payback.interviewapp.base.ui.FullScreenLoadingScreen
import com.payback.interviewapp.base.ui.dimen120
import com.payback.interviewapp.base.ui.dimen16
import com.payback.interviewapp.base.ui.dimen4
import com.payback.interviewapp.base.ui.dimen6
import com.payback.interviewapp.base.ui.dimen8
import com.payback.interviewapp.dashboard.ui.model.UiDashboardItem
import com.payback.interviewapp.dashboard.ui.model.mockUiDashboardItem
import com.payback.interviewapp.dashboard.ui.viewmodel.DashboardUiEvent
import com.payback.interviewapp.dashboard.ui.viewmodel.DashboardUiState

@Composable
internal fun DashboardScreen(uiState: DashboardUiState, onUiEvent: (DashboardUiEvent) -> Unit) {
    when (uiState) {
        is DashboardUiState.Loading -> FullScreenLoadingScreen()
        is DashboardUiState.Loaded -> DashboardBody(uiState = uiState, onUiEvent = onUiEvent)
        is DashboardUiState.Error -> DashboardError(onUiEvent = onUiEvent)
    }
}

@Composable
private fun DashboardBody(uiState: DashboardUiState.Loaded, onUiEvent: (DashboardUiEvent) -> Unit) {
    var alertDialogOpened by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<UiDashboardItem?>(null) }

    BaseScreen(
        title = stringResource(R.string.dashboard_title),
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DashboardSearchBar { query ->
                onUiEvent(DashboardUiEvent.FetchItems(query))
            }
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2)
            ) {
                items(uiState.dashboardItems) { item ->
                    CardItem(
                        item = item,
                        onCardClicked = {
                            selectedItem = item
                            alertDialogOpened = true
                        }
                    )
                }

            }
        }
    }

    if (alertDialogOpened && selectedItem != null) {
        DashboardAlertDialog(
            onConfirm = {
                onUiEvent(DashboardUiEvent.GoToDetailsScreen(selectedItem!!))
                alertDialogOpened = false
            },
            onDismiss = { alertDialogOpened = false }
        )
    }
}

@Composable
private fun DashboardError(onUiEvent: (DashboardUiEvent) -> Unit) {
    BaseScreen(
        title = stringResource(R.string.dashboard_title),
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DashboardSearchBar { query ->
                onUiEvent(DashboardUiEvent.FetchItems(query))
            }
            Spacer(modifier = Modifier.height(dimen120))
            Text(
                "Something went wrong.\nPlease try again.",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
internal fun DashboardSearchBar(onSearch: (String) -> Unit) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    Row(
        modifier = Modifier
            .padding(dimen8)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimen16)
    ) {
        OutlinedTextField(
            value = textState,
            onValueChange = { textState = it },
            label = { Text(stringResource(R.string.dashboard_search_bar_hint)) },
            modifier = Modifier.weight(1f)
        )
        Button(
            onClick = {
                val query = textState.text.trim().replace(" ", "+")
                onSearch(query)
            }
        ) {
            Text(
                stringResource(R.string.dashboard_search_bar_button_label),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Composable
internal fun CardItem(item: UiDashboardItem, onCardClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(dimen8)
            .clickable { onCardClicked() },
        elevation = CardDefaults.cardElevation(defaultElevation = dimen8),
    ) {
        Column {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimen120),
                elevation = CardDefaults.cardElevation(defaultElevation = dimen6),
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = rememberAsyncImagePainter(item.thumbnailUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Column(modifier = Modifier.padding(vertical = dimen4)) {
                Text(
                    text = "${Emojis.USER} @${item.username}",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(dimen8)
                )
                Text(
                    text = "${Emojis.TAG} ${item.tags.joinToString()}",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(dimen8)
                )
            }
        }
    }
}

@Composable
fun DashboardAlertDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        icon = {
            Icon(Icons.Rounded.Info, contentDescription = null)
        },
        title = {
            Text(text = stringResource(R.string.dashboard_dialog_title))
        },
        text = {
            Text(text = stringResource(R.string.dashboard_dialog_body))
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(stringResource(R.string.dashboard_dialog_confirm_button))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.dashboard_dialog_dismiss_button))
            }
        }
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DashboardScreenPreview() {
    InterviewAppTheme {
        Surface {
            DashboardScreen(
                uiState = DashboardUiState.Loaded(
                    dashboardItems = listOf(
                        mockUiDashboardItem,
                        mockUiDashboardItem,
                        mockUiDashboardItem,
                        mockUiDashboardItem,
                        mockUiDashboardItem
                    ),
                ),
                onUiEvent = {},
            )
        }
    }
}
