package com.fadhil.storyappexpert.core.data.source.local.entity

@androidx.room.Entity(tableName = "remote_keys")
data class RemoteKeys(
    @androidx.room.PrimaryKey val id: String,
    val prevKey: Int?,
    val nextKey: Int?
)