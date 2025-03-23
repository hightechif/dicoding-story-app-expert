package com.fadhil.storyappexpert.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "story_id")
    val storyId: String,
    @ColumnInfo(name = "created_time")
    val createdTime: Long?,
    @ColumnInfo(name = "favorite")
    val favorite: Boolean,
)
