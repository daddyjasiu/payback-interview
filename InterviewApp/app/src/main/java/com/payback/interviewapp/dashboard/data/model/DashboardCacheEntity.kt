package com.payback.interviewapp.dashboard.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.payback.interviewapp.di.DASHBOARD_CACHE_ENTITY_TABLE_NAME

@Entity(tableName = DASHBOARD_CACHE_ENTITY_TABLE_NAME)
data class DashboardCacheEntity(
    @PrimaryKey
    val id: Int,
    val thumbnailUrl: String,
    val largeImageUrl: String,
    val username: String,
    val tags: String,
    val likes: Int,
    val downloads: Int,
    val comments: Int,
)
