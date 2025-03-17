package com.fadhil.storyappexpert.core.data.source.remote

import com.fadhil.storyappexpert.core.data.Result
import com.fadhil.storyappexpert.core.data.source.remote.network.StoryApiService
import com.fadhil.storyappexpert.core.data.source.remote.request.ReqLogin
import com.fadhil.storyappexpert.core.data.source.remote.request.ReqRegister
import com.fadhil.storyappexpert.core.data.source.remote.response.ApiResponse
import com.fadhil.storyappexpert.core.data.source.remote.response.ResLogin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val service: StoryApiService
) : BaseRemoteDataSource() {

    fun register(
        name: String,
        email: String,
        password: String
    ): Flow<Result<ApiResponse<Any?>?>> = flow {
        val request = ReqRegister(name, email, password)
        emit(getResult { service.register(request) })
    }

    fun login(email: String, password: String): Flow<Result<ApiResponse<ResLogin>?>> = flow {
        val request = ReqLogin(email, password)
        emit(getResult { service.login(request) })
    }

}