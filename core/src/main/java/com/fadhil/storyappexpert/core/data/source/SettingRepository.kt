package com.fadhil.storyappexpert.core.data.source

import com.fadhil.storyappexpert.core.domain.repository.ISettingRepository

class SettingRepository @javax.inject.Inject constructor(
    private val localSource: com.fadhil.storyappexpert.core.data.source.local.SettingLocalDataSource
) : ISettingRepository {

    override fun getThemeSetting() = localSource.getThemeSetting()

}