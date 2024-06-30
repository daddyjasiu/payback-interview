package com.payback.interviewapp.details.ui.mapper

import com.payback.interviewapp.dashboard.data.model.DashboardResponse
import javax.inject.Inject

internal class DashboardUiMapper @Inject constructor() :
        (DashboardResponse) -> List<UiDashboardItem> {
    override fun invoke(dto: DashboardResponse): List<UiDashboardItem> = dto.hits.map {
        UiDashboardItem(
            url = it.previewURL,
            tags = it.tags.split(", "),
            username = it.user,
        )
    }

}