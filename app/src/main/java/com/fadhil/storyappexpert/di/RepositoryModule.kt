package com.fadhil.storyappexpert.di

import android.content.SharedPreferences
import com.fadhil.storyappexpert.data.source.AuthRepository
import com.fadhil.storyappexpert.data.source.ConfigurationRepository
import com.fadhil.storyappexpert.data.source.SettingRepository
import com.fadhil.storyappexpert.data.source.StoryPagingSource
import com.fadhil.storyappexpert.data.source.StoryRemoteMediator
import com.fadhil.storyappexpert.data.source.StoryRepository
import com.fadhil.storyappexpert.data.source.local.ConfigurationLocalDataSource
import com.fadhil.storyappexpert.data.source.local.SettingLocalDataSource
import com.fadhil.storyappexpert.data.source.local.StoryLocalDataSource
import com.fadhil.storyappexpert.data.source.local.db.AppDatabase
import com.fadhil.storyappexpert.data.source.local.db.StoryDao
import com.fadhil.storyappexpert.data.source.local.prefs.ConfigurationLocalSource
import com.fadhil.storyappexpert.data.source.local.prefs.HttpHeaderLocalSource
import com.fadhil.storyappexpert.data.source.local.prefs.SettingPreferences
import com.fadhil.storyappexpert.data.source.remote.AuthRemoteDataSource
import com.fadhil.storyappexpert.data.source.remote.StoryRemoteDataSource
import com.fadhil.storyappexpert.data.source.remote.network.StoryApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAuthRemoteDataSource(
        apiService: StoryApiService
    ) = AuthRemoteDataSource(apiService)

    @Singleton
    @Provides
    fun provideStoryRemoteDataSource(
        apiService: StoryApiService
    ) = StoryRemoteDataSource(apiService)

    @Singleton
    @Provides
    fun provideStoryRemoteMediator(
        database: AppDatabase,
        apiService: StoryApiService
    ) = StoryRemoteMediator(database, apiService)

    @Singleton
    @Provides
    fun provideConfigurationLocalSource(
        sharedPreferences: SharedPreferences
    ) = ConfigurationLocalSource(sharedPreferences)

    @Singleton
    @Provides
    fun provideConfigurationLocalDataSource(
        configurationLocalSource: ConfigurationLocalSource,
        httpHeaderLocalSource: HttpHeaderLocalSource,
    ) = ConfigurationLocalDataSource(configurationLocalSource, httpHeaderLocalSource)

    @Singleton
    @Provides
    fun provideSettingLocalDataSource(
        settingPreferences: SettingPreferences
    ) = SettingLocalDataSource(settingPreferences)

    @Singleton
    @Provides
    fun provideStoryLocalDataSource(
        storyDao: StoryDao
    ) = StoryLocalDataSource(storyDao)

    @Singleton
    @Provides
    fun provideStoryPagingSource(
        storyRemoteDataSource: StoryRemoteDataSource
    ) = StoryPagingSource(storyRemoteDataSource)

    @Singleton
    @Provides
    fun provideConfigurationRepository(
        configurationLocalDataSource: ConfigurationLocalDataSource
    ) = ConfigurationRepository(configurationLocalDataSource)

    @Singleton
    @Provides
    fun provideSettingRepository(
        settingLocalDataSource: SettingLocalDataSource
    ) = SettingRepository(settingLocalDataSource)

    @Singleton
    @Provides
    fun provideAuthRepository(
        authRemoteDataSource: AuthRemoteDataSource,
        configurationLocalDataSource: ConfigurationLocalDataSource
    ) = AuthRepository(authRemoteDataSource, configurationLocalDataSource)

    @Provides
    fun provideStoryRepository(
        storyRemoteDataSource: StoryRemoteDataSource,
        storyLocalDataSource: StoryLocalDataSource,
        storyRemoteMediator: StoryRemoteMediator,
        storyPagingSource: StoryPagingSource
    ) = StoryRepository.getInstance(
        storyRemoteDataSource,
        storyLocalDataSource,
        storyRemoteMediator,
        storyPagingSource
    )

}