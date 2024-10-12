package com.fadhil.storyappexpert.domain.usecase

import com.fadhil.storyappexpert.data.source.SettingRepository
import javax.inject.Inject

class SettingUseCase @Inject constructor(
    private val settingRepository: SettingRepository
): ISettingUseCase {

    override fun getThemeSetting() = settingRepository.getThemeSetting()

}