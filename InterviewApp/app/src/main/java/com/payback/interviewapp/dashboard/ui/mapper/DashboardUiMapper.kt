package com.payback.interviewapp.dashboard.ui.mapper

import com.payback.interviewapp.dashboard.data.model.DashboardResponse
import javax.inject.Inject

internal class DashboardUiMapper @Inject constructor() :
        (DashboardResponse) -> List<UiDashboardItem> {
    override fun invoke(dto: DashboardResponse): List<UiDashboardItem> = dto.hits.map {
        UiDashboardItem(
            thumbnailUrl = it.previewURL,
            largeImageUrl = it.largeImageURL,
            username = it.user,
            tags = it.tags.split(", "),
            likes = it.likes,
            downloads = it.downloads,
            comments = it.comments,
        )
    }
}