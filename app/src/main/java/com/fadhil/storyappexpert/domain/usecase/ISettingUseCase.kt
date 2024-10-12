package com.fadhil.storyappexpert.domain.usecase

import kotlinx.coroutines.flow.Flow

interface ISettingUseCase {

    fun getThemeSetting(): Flow<Boolean>

}