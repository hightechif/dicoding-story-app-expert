package com.fadhil.storyappexpert.core.domain.usecase

import com.fadhil.storyappexpert.core.data.Result
import com.fadhil.storyappexpert.core.data.source.remote.response.ApiResponse
import com.fadhil.storyappexpert.core.data.source.remote.response.ResLogin
import kotlinx.coroutines.flow.Flow

interface IAuthUseCase {

    fun register(name: String, email: String, password: String): Flow<Result<ApiResponse<Any?>?>>

    fun login(email: String, password: String): Flow<Result<ResLogin?>>

    fun logout(): Flow<Boolean>

}