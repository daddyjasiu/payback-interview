package com.payback.interviewapp.dashboard.data.service

import com.payback.interviewapp.dashboard.data.model.DashboardResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal const val DEFAULT_DASHBOARD_QUERY = "fruits"

internal interface DashboardService {
    @GET("api/")
    suspend fun getItems(
        @Query("key") key: String,
        @Query("q") query: String? = null,
        @Query("lang") language: String = "en",
        @Query("id") id: String? = null,
        @Query("image_type") imageType: String = "all",
        @Query("orientation") orientation: String = "all",
        @Query("category") category: String? = null,
        @Query("min_width") minWidth: Int = 0,
        @Query("min_height") minHeight: Int = 0,
        @Query("colors") colors: String? = null,
        @Query("editors_choice") editorsChoice: Boolean = false,
        @Query("safesearch") safeSearch: Boolean = false,
        @Query("order") order: String = "popular",
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20,
        @Query("callback") callback: String? = null,
        @Query("pretty") pretty: Boolean = false
    ): DashboardResponse
}