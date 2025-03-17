package com.fadhil.storyappexpert.core.data.source.local.prefs

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> by androidx.datastore.preferences.preferencesDataStore(
    name = "settings"
)

class SettingPreferences private constructor(private val dataStore: androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences>) {

    private val THEME_KEY =
        androidx.datastore.preferences.core.booleanPreferencesKey("theme_setting")

    fun getThemeSetting(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkModeActive
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: SettingPreferences? = null

        fun getInstance(dataStore: androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences>): SettingPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = SettingPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

}