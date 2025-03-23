package com.fadhil.storyappexpert.core.domain.model

import com.fadhil.storyappexpert.core.util.DateTimeUtil
import com.google.gson.Gson
import java.time.LocalDateTime

data class Story(
    val id: String,
    val name: String,
    val description: String,
    val photoUrl: String?,
    val createdDate: LocalDateTime,
    val lat: Double?,
    val lon: Double?,
    var favorite: Boolean?,
) {

    fun getCreatedDateDisplay(): String = DateTimeUtil.getUTCLocalDate(
        createdDate,
        "dd MMM yyyy, HH:mm:ss"
    )

    fun getCreatedTime() = createdDate.toInstant(DateTimeUtil.zoneOffsetUTC).toEpochMilli()

    override fun toString() = Gson().toJson(this)

    override fun equals(other: Any?): Boolean {
        return other != null && other is Story && (
                this.id == other.id &&
                        this.name == other.name &&
                        this.description == other.description &&
                        this.createdDate == other.createdDate &&
                        this.lat == other.lat &&
                        this.lon == other.lon &&
                        this.favorite == other.favorite
                )
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + (photoUrl?.hashCode() ?: 0)
        result = 31 * result + createdDate.hashCode()
        result = 31 * result + (lat?.hashCode() ?: 0)
        result = 31 * result + (lon?.hashCode() ?: 0)
        result = 31 * result + favorite.hashCode()
        return result
    }

}
