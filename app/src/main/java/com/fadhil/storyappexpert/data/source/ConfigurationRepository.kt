package com.fadhil.storyappexpert.data.source

import com.fadhil.storyappexpert.data.source.local.ConfigurationLocalDataSource
import com.fadhil.storyappexpert.domain.model.Configuration
import com.fadhil.storyappexpert.domain.model.Session
import com.fadhil.storyappexpert.domain.repository.IConfigurationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ConfigurationRepository @Inject constructor(
    private val configLocalDataSource: ConfigurationLocalDataSource,
) : IConfigurationRepository {

    override fun get() = flow {
        val cached = configLocalDataSource.getConfiguration()
        val isLogged = configLocalDataSource.isLogged()
        cached?.isLogin = isLogged
        emit(cached)
    }

    override fun update(session: Session, email: String): Flow<Configuration> = flow {
        var config = configLocalDataSource.getConfiguration()
        if (config == null) {
            config = Configuration(
                isLogin = true,
                email = email,
            )
        } else {
            config.isLogin = true
            config.email = email
        }

        configLocalDataSource.setBearerToken(session.token!!)
        configLocalDataSource.saveConfiguration(config)
        emit(config)
    }

}