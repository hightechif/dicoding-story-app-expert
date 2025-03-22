package com.fadhil.storyappexpert.core.data.source.local

import com.fadhil.storyappexpert.core.data.source.local.db.StoryDao
import com.fadhil.storyappexpert.core.data.source.local.entity.StoryEntity
import javax.inject.Inject

class StoryLocalDataSource @Inject constructor(
    private val dao: StoryDao
) {

    fun getStories() = dao.getStories()
    fun getPagingStories() = dao.getPagingStories()
    fun getFavorites() = dao.getFavorites()
    suspend fun updateData(story: StoryEntity) = dao.updateData(story)
    suspend fun deleteAll() = dao.deleteAll()

    suspend fun insertStory(stories: List<StoryEntity>) = dao.insertStory(stories)

}