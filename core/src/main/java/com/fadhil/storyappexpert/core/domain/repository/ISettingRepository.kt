package com.fadhil.storyappexpert.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface ISettingRepository {

    fun getThemeSetting(): Flow<Boolean>

}