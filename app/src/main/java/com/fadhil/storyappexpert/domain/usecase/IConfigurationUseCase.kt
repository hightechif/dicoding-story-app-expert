package com.fadhil.storyappexpert.domain.usecase

import com.fadhil.storyappexpert.domain.model.Configuration
import com.fadhil.storyappexpert.domain.model.Session
import kotlinx.coroutines.flow.Flow

interface IConfigurationUseCase {
    fun get(): Flow<Configuration?>
    fun update(session: Session, email: String): Flow<Configuration>
}