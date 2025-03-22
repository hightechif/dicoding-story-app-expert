package com.fadhil.storyappexpert.core.domain.model

data class Favorite(
    val id: String,
    val name: String,
    val description: String,
    val photoUrl: String?,
    val displayedDate: String,
    val lat: Double?,
    val lon: Double?,
    var favorite: Boolean = false,
)