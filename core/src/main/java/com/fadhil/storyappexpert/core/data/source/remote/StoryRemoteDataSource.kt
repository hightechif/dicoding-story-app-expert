package com.fadhil.storyappexpert.core.data.source.remote

import com.fadhil.storyappexpert.core.data.Result
import com.fadhil.storyappexpert.core.data.source.remote.network.StoryApiService
import com.fadhil.storyappexpert.core.data.source.remote.response.FileUploadResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class StoryRemoteDataSource @Inject constructor(
    private val service: StoryApiService
) : BaseRemoteDataSource() {

    fun addNewStory(
        photo: MultipartBody.Part,
        description: RequestBody,
        lat: RequestBody?,
        lon: RequestBody?,
    ): Flow<Result<FileUploadResponse?>> = flow {
        emit(getResult { service.addNewStory(photo, description, lat, lon) })
    }

    fun addNewStoryAsGuest(
        file: MultipartBody.Part,
        description: RequestBody
    ): Flow<Result<FileUploadResponse?>> = flow {
        emit(getResult { service.addNewStoryAsGuest(file, description) })
    }

    fun getAllStories(page: Int?, size: Int?, location: Int?) = flow {
        emit(getResult { service.getAllStories(page, size, location) })
    }

    fun getStoryDetail(id: String) = flow {
        emit(getResult { service.getStoryDetail(id) })
    }

}