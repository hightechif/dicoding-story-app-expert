package com.fadhil.storyappexpert.core.domain.usecase

import com.fadhil.storyappexpert.core.data.Result
import com.fadhil.storyappexpert.core.data.source.remote.response.ApiResponse
import com.fadhil.storyappexpert.core.data.source.remote.response.ResLogin
import com.fadhil.storyappexpert.core.domain.repository.IAuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val repository: IAuthRepository
) : IAuthUseCase {

    override fun register(
        name: String,
        email: String,
        password: String
    ): Flow<Result<ApiResponse<Any?>?>> =
        repository.register(name, email, password)

    override fun login(email: String, password: String): Flow<Result<ResLogin?>> =
        repository.login(email, password)

    override fun logout(): Flow<Boolean> = repository.logout()

}