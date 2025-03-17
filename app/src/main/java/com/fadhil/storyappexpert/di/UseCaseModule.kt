package com.fadhil.storyappexpert.di

import com.fadhil.storyappexpert.core.data.source.AuthRepository
import com.fadhil.storyappexpert.core.data.source.ConfigurationRepository
import com.fadhil.storyappexpert.core.data.source.SettingRepository
import com.fadhil.storyappexpert.core.domain.repository.IStoryRepository
import com.fadhil.storyappexpert.core.domain.usecase.AuthUseCase
import com.fadhil.storyappexpert.core.domain.usecase.ConfigurationUseCase
import com.fadhil.storyappexpert.core.domain.usecase.SettingUseCase
import com.fadhil.storyappexpert.core.domain.usecase.StoryUseCase
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