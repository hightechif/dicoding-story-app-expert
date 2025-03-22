package com.fadhil.storyappexpert.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fadhil.storyappexpert.core.util.DateTimeUtil
import java.time.Instant
import java.time.LocalDateTime

@Entity(tableName = "stories")
data class StoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "photoUrl")
    val photoUrl: String?,
    @ColumnInfo(name = "created_time")
    val createdTime: Long,
    @ColumnInfo(name = "lat")
    val lat: Double?,
    @ColumnInfo(name = "lon")
    val lon: Double?,
    @ColumnInfo(name = "favorite")
    val favorite: Boolean = false
) {

    fun getCreatedLocalDateTime(): LocalDateTime =
        Instant.ofEpochMilli(createdTime).atZone(DateTimeUtil.zoneIdUTC).toLocalDateTime()

    override fun toString() = "$id::$name::$favorite::$createdTime"

}