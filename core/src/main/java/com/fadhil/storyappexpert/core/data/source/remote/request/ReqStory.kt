package com.fadhil.storyappexpert.core.data.source.remote.request

import java.io.File

data class ReqStory(
    val description: String,
    val photo: File,
    val lat: Double?,
    val lon: Double?
)
