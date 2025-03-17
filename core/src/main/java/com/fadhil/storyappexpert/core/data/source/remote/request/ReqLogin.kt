package com.fadhil.storyappexpert.core.data.source.remote.request

data class ReqLogin(
    @field:com.google.gson.annotations.SerializedName("email")
    val email: String,
    @field:com.google.gson.annotations.SerializedName("password")
    val password: String
)
