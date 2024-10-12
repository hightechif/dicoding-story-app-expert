package com.fadhil.storyappexpert.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class ReqRegister(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("email")
    val email: String,
    @field:SerializedName("password")
    val password: String
) {

    fun toLoginRequest(): ReqLogin = ReqLogin(
        email = email,
        password = password,
    )

}
