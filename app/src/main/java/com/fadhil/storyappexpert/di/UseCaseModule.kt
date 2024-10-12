package com.fadhil.storyappexpert.di

import com.fadhil.storyappexpert.data.source.AuthRepository
import com.fadhil.storyappexpert.data.source.ConfigurationRepository
import com.fadhil.storyappexpert.data.source.SettingRepository
import com.fadhil.storyappexpert.domain.repository.IStoryRepository
import com.fadhil.storyappexpert.domain.usecase.AuthUseCase
import com.fadhil.storyappexpert.domain.usecase.ConfigurationUseCase
import com.fadhil.storyappexpert.domain.usecase.SettingUseCase
import com.fadhil.storyappexpert.domain.usecase.StoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideConfigurationUseCase(
        configurationRepository: ConfigurationRepository
    ) = ConfigurationUseCase(configurationRepository)

    @Singleton
    @Provides
    fun provideSettingUseCase(
        settingRepository: SettingRepository
    ) = SettingUseCase(settingRepository)

    @Singleton
    @Provides
    fun provideAuthUseCase(
        authRepository: AuthRepository
    ) = AuthUseCase(authRepository)

    @Singleton
    @Provides
    fun provideStoryUseCase(
        storyRepository: IStoryRepository
    ) = StoryUseCase.getInstance(storyRepository)

}