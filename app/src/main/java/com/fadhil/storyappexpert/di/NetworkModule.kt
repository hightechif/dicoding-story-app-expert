package com.fadhil.storyappexpert.di

import android.content.SharedPreferences
import com.fadhil.storyappexpert.BuildConfig
import com.fadhil.storyappexpert.core.data.source.local.prefs.HttpHeaderLocalSource
import com.fadhil.storyappexpert.core.data.source.remote.network.AuthInterceptor
import com.fadhil.storyappexpert.core.data.source.remote.network.StoryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpHeaderLocalSource(
        sharedPreferences: SharedPreferences
    ) = HttpHeaderLocalSource(sharedPreferences)

    @Singleton
    @Provides
    fun provideLoggerInterceptor(): HttpLoggingInterceptor = StoryApi.buildLoggerInterceptor()

    @Singleton
    @Provides
    fun provideAuthInterceptor(httpHeaderLocalSource: HttpHeaderLocalSource): AuthInterceptor =
        StoryApi.buildAuthInterceptor(httpHeaderLocalSource)

    @Singleton
    @Provides
    fun provideHttpClient(
        loggerInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient = StoryApi.buildClient(loggerInterceptor, authInterceptor, BuildConfig.DEBUG)

    @Singleton
    @Provides
    fun provideRetrofit(
        httpClient: OkHttpClient
    ): Retrofit = StoryApi.buildRetrofit(httpClient)

}