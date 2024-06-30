package com.payback.interviewapp.dashboard.data.model

import com.google.gson.annotations.SerializedName

internal data class DashboardRequest(
    @SerializedName("key")
    val key: String,
    @SerializedName("q")
    val query: String? = null,
    @SerializedName("lang")
    val language: String = "en",
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("image_type")
    val imageType: String = "all",
    @SerializedName("orientation")
    val orientation: String = "all",
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("min_width")
    val minWidth: Int = 0,
    @SerializedName("min_height")
    val minHeight: Int = 0,
    @SerializedName("colors")
    val colors: String? = null,
    @SerializedName("editors_choice")
    val editorsChoice: Boolean = false,
    @SerializedName("safesearch")
    val safeSearch: Boolean = false,
    @SerializedName("order")
    val order: String = "popular",
    @SerializedName("page")
    val page: Int = 1,
    @SerializedName("per_page")
    val perPage: Int = 20,
    @SerializedName("callback")
    val callback: String? = null,
    @SerializedName("pretty")
    val pretty: Boolean = false
)