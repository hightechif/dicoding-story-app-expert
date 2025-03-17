package com.fadhil.storyappexpert.core.domain.usecase

import com.fadhil.storyappexpert.core.domain.model.Configuration
import com.fadhil.storyappexpert.core.domain.model.Session
import kotlinx.coroutines.flow.Flow

interface IConfigurationUseCase {
    fun get(): Flow<Configuration?>
    fun update(session: Session, email: String): Flow<Configuration>
}