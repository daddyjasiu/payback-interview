package com.payback.interviewapp.dashboard.data.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.payback.interviewapp.dashboard.data.model.DashboardCacheEntity

@Dao
internal interface DashboardItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<DashboardCacheEntity>)

    @Query("SELECT * FROM dashboard_items")
    fun getAllItems(): List<DashboardCacheEntity>

    @Query("DELETE FROM dashboard_items")
    suspend fun clearAll()
}
