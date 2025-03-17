package com.fadhil.storyappexpert.core.domain.usecase

import kotlinx.coroutines.flow.Flow

interface ISettingUseCase {

    fun getThemeSetting(): Flow<Boolean>

}