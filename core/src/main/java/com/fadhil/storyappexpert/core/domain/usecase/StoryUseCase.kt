package com.fadhil.storyappexpert.core.domain.usecase

import android.content.Context
import android.net.Uri
import com.fadhil.storyappexpert.core.domain.model.Story
import com.fadhil.storyappexpert.core.domain.repository.IStoryRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class StoryUseCase @Inject constructor(
    private val storyRepository: IStoryRepository
) : IStoryUseCase {

    override fun getAllStory(
        page: Int?,
        size: Int?,
        location: Int?,
        reload: Boolean
    ) = storyRepository.getAllStories(page, size, location, reload)

    override fun getPagingStory(
        size: Int?,
        location: Int?
    ) = storyRepository.getPagingStory(size, location)

    override fun getStoryDetail(id: String) =
        storyRepository.getStoryDetail(id)

    override fun addNewStory(
        context: Context,
        description: String,
        uri: Uri,
        lat: Double?,
        lon: Double?
    ) = storyRepository.addNewStory(context, description, uri, lat, lon)

    override suspend fun addToFavorites(story: Story) = flow {
        try {
            story.favorite = !story.favorite
            storyRepository.addToFavorites(story)
            emit(true)
        } catch (e: Exception) {
            e.printStackTrace()
            emit(false)
        }
    }

    companion object {
        @Volatile
        private var instance: IStoryUseCase? = null
        fun getInstance(storyRepository: IStoryRepository): IStoryUseCase =
            instance ?: synchronized(this) {
                instance ?: StoryUseCase(storyRepository)
            }.also { instance = it }
    }
}