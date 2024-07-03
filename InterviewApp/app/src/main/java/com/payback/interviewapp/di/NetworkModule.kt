package com.payback.interviewapp.di

import com.payback.interviewapp.dashboard.data.service.DashboardItemDao
import com.payback.interviewapp.dashboard.data.repository.DashboardRepository
import com.payback.interviewapp.dashboard.data.service.DashboardService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val PIXABAY_BASE_API_URL = "https://pixabay.com/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(PIXABAY_BASE_API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideDashboardService(retrofit: Retrofit): DashboardService {
        return retrofit.create(DashboardService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideDashboardRepository(
        service: DashboardService,
        dao: DashboardItemDao,
    ): DashboardRepository {
        return DashboardRepository(service, dao)
    }
}