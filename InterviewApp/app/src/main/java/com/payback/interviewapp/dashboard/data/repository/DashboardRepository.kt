package com.payback.interviewapp.dashboard.data.repository

import com.payback.interviewapp.dashboard.data.model.DashboardRequest
import com.payback.interviewapp.dashboard.data.model.DashboardResponse
import com.payback.interviewapp.dashboard.data.service.DashboardService
import javax.inject.Inject

internal class DashboardRepository @Inject constructor(
    private val service: DashboardService,
) {
    suspend fun getImages(request: DashboardRequest): DashboardResponse {
        return service.getImages(
            key = request.key,
            query = request.query,
            language = request.language,
            id = request.id,
            imageType = request.imageType,
            orientation = request.orientation,
            category = request.category,
            minWidth = request.minWidth,
            minHeight = request.minHeight,
            colors = request.colors,
            editorsChoice = request.editorsChoice,
            safeSearch = request.safeSearch,
            order = request.order,
            page = request.page,
            perPage = request.perPage,
            callback = request.callback,
            pretty = request.pretty
        )
    }
}