package com.payback.interviewapp.dashboard.ui.mapper

import com.payback.interviewapp.dashboard.data.model.DashboardCacheEntity
import com.payback.interviewapp.dashboard.data.model.DashboardResponse
import com.payback.interviewapp.dashboard.ui.model.UiDashboardItem
import javax.inject.Inject

internal class DashboardUiMapper @Inject constructor() :
        (DashboardResponse) -> List<UiDashboardItem> {
    override fun invoke(dto: DashboardResponse): List<UiDashboardItem> = dto.hits.map {
        UiDashboardItem(
            id = it.id,
            thumbnailUrl = it.previewURL,
            largeImageUrl = it.largeImageURL,
            username = it.user,
            tags = it.tags.split(", "),
            likes = it.likes,
            downloads = it.downloads,
            comments = it.comments,
        )
    }

    fun mapUiModelToCacheEntity(items: List<UiDashboardItem>) = items.map {
        DashboardCacheEntity(
            id = it.id,
            username = it.username,
            thumbnailUrl = it.thumbnailUrl,
            tags = it.tags.joinToString(),
            largeImageUrl = it.largeImageUrl,
            likes = it.likes,
            downloads = it.downloads,
            comments = it.comments,
        )
    }

    fun mapCacheEntityToUiModel(entities: List<DashboardCacheEntity>) = entities.map {
        UiDashboardItem(
            id = it.id,
            username = it.username,
            thumbnailUrl = it.thumbnailUrl,
            tags = it.tags.split(", "),
            largeImageUrl = it.largeImageUrl,
            likes = it.likes,
            downloads = it.downloads,
            comments = it.comments,
        )
    }
}