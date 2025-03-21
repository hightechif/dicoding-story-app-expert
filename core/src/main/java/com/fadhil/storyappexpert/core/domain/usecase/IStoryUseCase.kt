package com.fadhil.storyappexpert.core.domain.usecase

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.fadhil.storyappexpert.core.data.Result
import com.fadhil.storyappexpert.core.data.source.remote.response.FileUploadResponse
import com.fadhil.storyappexpert.core.domain.model.Story
import kotlinx.coroutines.flow.Flow

interface IStoryUseCase {

    fun getAllStory(
        page: Int?,
        size: Int?,
        location: Int?,
        reload: Boolean
    ): Flow<Result<List<Story>>>

    fun getPagingStory(
        size: Int?,
        location: Int?
    ): LiveData<PagingData<Story>>

    fun getStoryDetail(id: String): Flow<Result<Story?>>

    fun addNewStory(
        context: Context,
        description: String,
        uri: Uri,
        lat: Double?,
        lon: Double?
    ): Flow<Result<FileUploadResponse?>>

    suspend fun addToFavorites(story: Story): Flow<Boolean>

}