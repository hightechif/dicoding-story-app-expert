package com.fadhil.storyappexpert.domain.usecase

import com.fadhil.core.data.Result
import com.fadhil.storyappexpert.data.source.remote.response.ApiResponse
import com.fadhil.storyappexpert.data.source.remote.response.ResLogin
import kotlinx.coroutines.flow.Flow

interface IAuthUseCase {

    fun register(name: String, email: String, password: String): Flow<Result<ApiResponse<Any?>?>>

    fun login(email: String, password: String): Flow<Result<ResLogin?>>

    fun logout(): Flow<Boolean>

}