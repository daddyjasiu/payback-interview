package com.payback.interviewapp.di

import android.content.Context
import androidx.navigation.NavHostController
import com.payback.interviewapp.base.navigation.NavigationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {
    @Provides
    @Singleton
    fun provideNavController(@ApplicationContext context: Context): NavHostController {
        return NavigationService(context).navController
    }
}