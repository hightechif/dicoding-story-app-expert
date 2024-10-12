package com.fadhil.storyappexpert.domain.repository

import com.fadhil.storyappexpert.domain.model.Configuration
import com.fadhil.storyappexpert.domain.model.Session
import kotlinx.coroutines.flow.Flow

interface IConfigurationRepository {
    fun get(): Flow<Configuration?>
    fun update(session: Session, username: String): Flow<Configuration>
}