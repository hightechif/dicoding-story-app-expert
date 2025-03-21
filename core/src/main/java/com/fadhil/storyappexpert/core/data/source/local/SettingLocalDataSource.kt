package com.fadhil.storyappexpert.core.data.source.local

import com.fadhil.storyappexpert.core.data.source.local.prefs.SettingPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingLocalDataSource @Inject constructor(
    private val prefs: SettingPreferences
) {

    fun getThemeSetting(): Flow<Boolean> = prefs.getThemeSetting()

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) =
        prefs.saveThemeSetting(isDarkModeActive)

}