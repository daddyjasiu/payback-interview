package com.payback.interviewapp.base

import com.payback.interviewapp.dashboard.data.service.DashboardItemDao
import androidx.room.Database
import androidx.room.RoomDatabase
import com.payback.interviewapp.dashboard.data.model.DashboardCacheEntity

@Database(entities = [DashboardCacheEntity::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun dashboardItemDao(): DashboardItemDao
}
