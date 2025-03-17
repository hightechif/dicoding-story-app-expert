package com.fadhil.storyappexpert.core.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class NetworkBoundProcessResource<ResultType, RequestType> {
    private val result: Flow<Result<ResultType>> = flow {
        emit(Result.Loading())
        createCall().collect { response ->
            when (response.status) {
                Result.Status.SUCCESS -> {
                    val responseData = callBackResult(response.data!!)
                    emit(Result.Success(responseData))
                }
                Result.Status.UNAUTHORIZED -> {
                    emit(Result.Unauthorized(response.message, onResponseError(response.data)))
                }
                else -> {
                    emit(
                        Result.Error(
                            response.code,
                            response.message
                        )
                    )
                }
            }
        }
    }

    protected open suspend fun onResponseError(data: Any?): ResultType? {
        return null
    }

    protected abstract suspend fun createCall(): Flow<Result<RequestType>>

    protected abstract suspend fun callBackResult(data: RequestType): ResultType

    fun asFlow(): Flow<Result<ResultType>> = result
}