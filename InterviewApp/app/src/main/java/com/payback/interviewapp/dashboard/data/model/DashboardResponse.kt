package com.payback.interviewapp.dashboard.data.model

import com.google.gson.annotations.SerializedName

internal data class DashboardResponse(
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalHits")
    val totalHits: Int,
    @SerializedName("hits")
    val hits: List<PixabayHit>
)
