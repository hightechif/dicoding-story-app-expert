package com.fadhil.storyappexpert.core.data.source.remote.response

data class ApiContentResponse<T>(
    @field:com.google.gson.annotations.SerializedName("error")
    val error: Boolean,
    @field:com.google.gson.annotations.SerializedName("message")
    val message: String,
    @field:com.google.gson.annotations.SerializedName("listStory")
    val listStory: List<T>
) {
    fun isSuccess() = !error
}
