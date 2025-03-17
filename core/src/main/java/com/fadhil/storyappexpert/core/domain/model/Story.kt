package com.fadhil.storyappexpert.core.domain.model

import com.fadhil.storyappexpert.core.util.DateTimeUtil
import java.time.LocalDateTime

data class Story(
    val id: String,
    val name: String,
    val description: String,
    val photoUrl: String?,
    val createdDate: LocalDateTime,
    val lat: Double?,
    val lon: Double?,
    var favorite: Boolean = false
) {

    fun getCreatedDateDisplay(): String = DateTimeUtil.getUTCLocalDate(
        createdDate,
        "dd MMM yyyy, HH:mm:ss"
    )

    fun getCreatedTime() = createdDate.toInstant(DateTimeUtil.zoneOffsetUTC).toEpochMilli()

}
