package com.payback.interviewapp.details.ui.view

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.payback.interviewapp.R
import com.payback.interviewapp.base.theme.InterviewAppTheme
import com.payback.interviewapp.base.ui.BaseScreen
import com.payback.interviewapp.base.ui.Emojis
import com.payback.interviewapp.base.ui.dimen12
import com.payback.interviewapp.base.ui.dimen16
import com.payback.interviewapp.base.ui.dimen256
import com.payback.interviewapp.base.ui.dimen8
import com.payback.interviewapp.dashboard.ui.mapper.UiDashboardItem
import com.payback.interviewapp.dashboard.ui.mapper.mockUiDashboardItem
import com.payback.interviewapp.details.ui.viewmodel.DetailsUiEvent

@Composable
internal fun DetailsScreen(dashboardItem: UiDashboardItem, onUiEvent: (DetailsUiEvent) -> Unit) {
    BaseScreen(
        title = stringResource(R.string.details_title),
        onBackButtonPressed = { onUiEvent(DetailsUiEvent.GoBack) },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Column(modifier = Modifier.padding(dimen12)) {
                ElevatedCard(
                    modifier = Modifier.height(dimen256),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = dimen8
                    ),
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = rememberAsyncImagePainter(model = dashboardItem.largeImageUrl),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }
                Column(
                    modifier = Modifier.padding(vertical = dimen16),
                    verticalArrangement = Arrangement.spacedBy(dimen12)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(dimen12)
                    ) {
                        DetailsEmojiText(
                            emoji = Emojis.LIKE,
                            text = dashboardItem.likes.toString(),
                        )
                        DetailsEmojiText(
                            emoji = Emojis.COMMENT,
                            text = dashboardItem.comments.toString(),
                        )
                        DetailsEmojiText(
                            emoji = Emojis.DOWNLOAD,
                            text = dashboardItem.downloads.toString(),
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        DetailsEmojiText(
                            emoji = Emojis.USER,
                            text = dashboardItem.username,
                            isUsername = true,
                        )
                    }
                    HorizontalDivider()
                    DetailsEmojiText(
                        emoji = Emojis.TAG,
                        text = dashboardItem.tags.joinToString(),
                    )
                }
            }
        }
    }
}

@Composable
private fun DetailsEmojiText(emoji: String, text: String, isUsername: Boolean = false) {
    Text(
        text = "$emoji $text",
        style = if (isUsername) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyLarge,
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DetailsScreenPreview() {
    InterviewAppTheme {
        Surface {
            DetailsScreen(
                dashboardItem = mockUiDashboardItem,
                onUiEvent = {},
            )
        }
    }
}