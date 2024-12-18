package com.fadhil.storyappexpert.data.source.local

import com.fadhil.storyappexpert.data.source.local.db.StoryDao
import com.fadhil.storyappexpert.data.source.local.entity.StoryEntity
import javax.inject.Inject

class StoryLocalDataSource @Inject constructor(
    private val dao: StoryDao
) {

    fun getStories() = dao.getStories()
    fun getPagingStories() = dao.getPagingStories()
    suspend fun deleteAll() = dao.deleteAll()

    suspend fun insertStory(stories: List<StoryEntity>) = dao.insertStory(stories)

}