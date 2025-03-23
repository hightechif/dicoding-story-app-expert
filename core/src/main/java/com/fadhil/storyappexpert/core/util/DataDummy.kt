package com.fadhil.storyappexpert.core.util

import com.fadhil.storyappexpert.core.domain.model.Story
import java.time.LocalDateTime

object DataDummy {
    fun generateDummyValidStory(): List<Story> {
        val list = listOf(
            Story("1", "Fadhil", "Well", null, LocalDateTime.now(), null, null, false),
            Story("2", "Fadhil", "Well", null, LocalDateTime.now(), null, null, false),
            Story("3", "Fadhil", "Well", null, LocalDateTime.now(), null, null, false),
            Story("4", "Fadhil", "Well", null, LocalDateTime.now(), null, null, false),
            Story("5", "Fadhil", "Well", null, LocalDateTime.now(), null, null, false),
            Story("6", "Fadhil", "Well", null, LocalDateTime.now(), null, null, false),
            Story("7", "Fadhil", "Well", null, LocalDateTime.now(), null, null, false),
            Story("8", "Fadhil", "Well", null, LocalDateTime.now(), null, null, false),
            Story("9", "Fadhil", "Well", null, LocalDateTime.now(), null, null, false),
            Story("10", "Fadhil", "Well", null, LocalDateTime.now(), null, null, false),
        )
        return list
    }

    fun generateDummyEmptyStory(): List<Story> {
        return emptyList()
    }
}