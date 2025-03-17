package com.fadhil.storyappexpert.core.data.source.remote

import com.fadhil.storyappexpert.core.data.Result
import com.fadhil.storyappexpert.core.data.source.remote.network.StoryApiService
import com.fadhil.storyappexpert.core.data.source.remote.request.ReqLogin
import com.fadhil.storyappexpert.core.data.source.remote.request.ReqRegister
import com.fadhil.storyappexpert.core.data.source.remote.response.ApiResponse
import com.fadhil.storyappexpert.core.data.source.remote.response.ResLogin
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val service: StoryApiService
) : BaseRemoteDataSource() {

    suspend fun register(
        name: String,
        email: String,
        password: String
    ): Result<ApiResponse<Any?>?> {
        val request = ReqRegister(name, email, password)
        return getResult { service.register(request) }
    }

    suspend fun login(email: String, password: String): Result<ApiResponse<ResLogin>?> {
        val request = ReqLogin(email, password)
        return getResult { service.login(request) }
    }

}