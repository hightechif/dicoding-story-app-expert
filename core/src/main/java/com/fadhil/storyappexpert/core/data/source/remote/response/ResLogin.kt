package com.fadhil.storyappexpert.core.data.source.remote.response

data class ResLogin(
    @field:com.google.gson.annotations.SerializedName("userId")
    val userId: String,
    @field:com.google.gson.annotations.SerializedName("name")
    val name: String,
    @field:com.google.gson.annotations.SerializedName("token")
    val token: String
)
