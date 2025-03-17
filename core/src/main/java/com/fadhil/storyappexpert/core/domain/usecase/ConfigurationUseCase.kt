package com.fadhil.storyappexpert.core.domain.usecase

import com.fadhil.storyappexpert.core.domain.model.Configuration
import com.fadhil.storyappexpert.core.domain.model.Session
import com.fadhil.storyappexpert.core.domain.repository.IConfigurationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConfigurationUseCase @Inject constructor(
    private val repository: IConfigurationRepository
) : IConfigurationUseCase {

    override fun get(): Flow<Configuration?> = repository.get()

    override fun update(session: Session, email: String): Flow<Configuration> =
        repository.update(session, email)

}