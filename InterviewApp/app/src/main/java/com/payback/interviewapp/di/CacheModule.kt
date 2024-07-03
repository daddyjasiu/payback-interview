package com.payback.interviewapp.di

import android.content.Context
import androidx.room.Room
import com.payback.interviewapp.base.AppDatabase
import com.payback.interviewapp.dashboard.data.service.DashboardItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val DASHBOARD_CACHE_ENTITY_TABLE_NAME = "dashboard_items"
const val APP_DATABASE_NAME = "app_database"

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {
    @Provides
    @Singleton
    internal fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            APP_DATABASE_NAME
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    internal fun provideDashboardItemDao(database: AppDatabase): DashboardItemDao {
        return database.dashboardItemDao()
    }
}