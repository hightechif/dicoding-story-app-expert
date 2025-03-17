package com.fadhil.storyappexpert.core.data.source.remote.response

data class ApiResponse<T>(
    @field:com.google.gson.annotations.SerializedName("error")
    val error: Boolean,
    @field:com.google.gson.annotations.SerializedName("message")
    val message: String,
    @field:com.google.gson.annotations.SerializedName("loginResult")
    val loginResult: T?,
    @field:com.google.gson.annotations.SerializedName("story")
    val story: T?
) {
    fun isSuccess() = !error
}
