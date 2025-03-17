package com.fadhil.storyappexpert.core.domain.model

data class Configuration (
    var isLogin: Boolean = false,
    var email: String?,
    var firstLogin: Boolean = false
)