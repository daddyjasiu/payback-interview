package com.payback.interviewapp.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        CacheModule::class,
        NetworkModule::class,
        NavigationModule::class,
    ]
)
@InstallIn(SingletonComponent::class)
object AppModule