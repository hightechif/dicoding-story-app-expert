package com.fadhil.storyappexpert.domain.model

data class Configuration (
    var isLogin: Boolean = false,
    var email: String?,
    var firstLogin: Boolean = false
)