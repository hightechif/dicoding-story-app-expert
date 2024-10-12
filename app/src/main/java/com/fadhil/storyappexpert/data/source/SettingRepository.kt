package com.fadhil.storyappexpert.data.source

import com.fadhil.storyappexpert.data.source.local.SettingLocalDataSource
import com.fadhil.storyappexpert.domain.repository.ISettingRepository
import javax.inject.Inject

class SettingRepository @Inject constructor(
    private val localSource: SettingLocalDataSource
) : ISettingRepository {

    override fun getThemeSetting() = localSource.getThemeSetting()

}