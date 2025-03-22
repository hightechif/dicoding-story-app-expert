package com.fadhil.storyappexpert.core.di

import android.content.Context
import android.content.SharedPreferences
import com.fadhil.storyappexpert.core.data.source.local.db.AppDatabase
import com.fadhil.storyappexpert.core.data.source.local.prefs.SettingPreferences
import com.fadhil.storyappexpert.core.data.source.local.prefs.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase =
        AppDatabase.getDatabase(
            appContext.applicationContext,
            AppDatabase.DATABASE_NAME
        )

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences =
        androidx.preference.PreferenceManager.getDefaultSharedPreferences(appContext)

    @Singleton
    @Provides
    fun provideSettingPreferences(@ApplicationContext appContext: Context): SettingPreferences =
        SettingPreferences.getInstance(appContext.dataStore)

    @Singleton
    @Provides
    fun provideStoryDao(db: AppDatabase) = db.storyDao()

}