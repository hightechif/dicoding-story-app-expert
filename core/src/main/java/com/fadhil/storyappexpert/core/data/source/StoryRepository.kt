package com.fadhil.storyappexpert.core.data.source

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.PagingData
import androidx.paging.liveData
import androidx.paging.map
import com.fadhil.storyappexpert.core.data.NetworkBoundProcessResource
import com.fadhil.storyappexpert.core.data.Result
import com.fadhil.storyappexpert.core.domain.mapper.StoryMapper
import com.fadhil.storyappexpert.core.domain.model.Story
import com.fadhil.storyappexpert.core.domain.repository.IStoryRepository
import com.fadhil.storyappexpert.core.util.FileUtil
import com.fadhil.storyappexpert.core.util.reduceFileImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.mapstruct.factory.Mappers
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class StoryRepository @javax.inject.Inject constructor(
    private val remoteDataSource: com.fadhil.storyappexpert.core.data.source.remote.StoryRemoteDataSource,
    private val localDataSource: com.fadhil.storyappexpert.core.data.source.local.StoryLocalDataSource,
    private val storyRemoteMediator: StoryRemoteMediator,
    private val storyPagingSource: StoryPagingSource
) : IStoryRepository {

    private val mapper = Mappers.getMapper(StoryMapper::class.java)

    override fun addNewStory(
        context: Context,
        description: String,
        uri: Uri,
        lat: Double?,
        lon: Double?
    ): Flow<Result<com.fadhil.storyappexpert.core.data.source.remote.response.FileUploadResponse?>> =
        object :
            NetworkBoundProcessResource<com.fadhil.storyappexpert.core.data.source.remote.response.FileUploadResponse?, com.fadhil.storyappexpert.core.data.source.remote.response.FileUploadResponse?>() {
            override suspend fun createCall(): Result<com.fadhil.storyappexpert.core.data.source.remote.response.FileUploadResponse?> {
                val imageFile = uriToFile(uri, context).reduceFileImage()

                val multipartBody = createPart("photo", imageFile)
                val descReqBody = description.toRequestBody("text/plain".toMediaType())
                val latReqBody = lat?.toString()?.toRequestBody("text/plain".toMediaType())
                val lonReqBody = lon?.toString()?.toRequestBody("text/plain".toMediaType())

                return remoteDataSource.addNewStory(
                    multipartBody,
                    descReqBody,
                    latReqBody,
                    lonReqBody
                )
            }

            override suspend fun callBackResult(data: com.fadhil.storyappexpert.core.data.source.remote.response.FileUploadResponse?): com.fadhil.storyappexpert.core.data.source.remote.response.FileUploadResponse? {
                return data
            }
        }.asFlow()

    override fun addNewStoryAsGuest(
        context: Context,
        description: String,
        uri: Uri,
        lat: Double?,
        lon: Double?
    ): Flow<Result<com.fadhil.storyappexpert.core.data.source.remote.response.FileUploadResponse?>> =
        object :
            NetworkBoundProcessResource<com.fadhil.storyappexpert.core.data.source.remote.response.FileUploadResponse?, com.fadhil.storyappexpert.core.data.source.remote.response.FileUploadResponse?>() {
            override suspend fun createCall(): Result<com.fadhil.storyappexpert.core.data.source.remote.response.FileUploadResponse?> {
                val imageFile = uriToFile(uri, context).reduceFileImage()

                val multipartBody = createPart("photo-guest", imageFile)
                val requestBody = description.toRequestBody("text/plain".toMediaType())

                return remoteDataSource.addNewStoryAsGuest(multipartBody, requestBody)
            }

            override suspend fun callBackResult(data: com.fadhil.storyappexpert.core.data.source.remote.response.FileUploadResponse?): com.fadhil.storyappexpert.core.data.source.remote.response.FileUploadResponse? {
                return data
            }
        }.asFlow()

    private fun createPart(
        imageName: String,
        imageFile: File
    ): okhttp3.MultipartBody.Part {
        val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
        return okhttp3.MultipartBody.Part.createFormData(
            imageName,
            imageFile.name,
            requestImageFile
        )
    }

    override fun getAllStories(
        page: Int?,
        size: Int?,
        location: Int?,
        reload: Boolean
    ): Flow<Result<List<Story>>> =
        object :
            NetworkBoundProcessResource<List<Story>, com.fadhil.storyappexpert.core.data.source.remote.response.ApiContentResponse<com.fadhil.storyappexpert.core.data.source.remote.response.ResStory>?>() {

            override suspend fun createCall(): Result<com.fadhil.storyappexpert.core.data.source.remote.response.ApiContentResponse<com.fadhil.storyappexpert.core.data.source.remote.response.ResStory>?> {
                return remoteDataSource.getAllStories(page, size, location)
            }

            override suspend fun callBackResult(data: com.fadhil.storyappexpert.core.data.source.remote.response.ApiContentResponse<com.fadhil.storyappexpert.core.data.source.remote.response.ResStory>?): List<Story> {
                return mapper.mapStoryResponseToDomainList(data?.listStory ?: emptyList())
            }

        }.asFlow()

    @OptIn(androidx.paging.ExperimentalPagingApi::class)
    override fun getPagingStory(
        size: Int?,
        location: Int?
    ): LiveData<PagingData<Story>> {
        storyPagingSource.location = location ?: 1
        storyRemoteMediator.location = location ?: 1
        val pagingSourceFactory = {
            // storyPagingSource
            localDataSource.getPagingStories()
        }
        val pager = androidx.paging.Pager(
            config = androidx.paging.PagingConfig(
                pageSize = 10,
            ),
            remoteMediator = storyRemoteMediator,
            pagingSourceFactory = pagingSourceFactory
        )

        return pager.liveData.map { pagingData ->
            pagingData.map {
                mapper.mapStoryEntityToDomain(it)
            }
        }
    }

    override fun getStoryDetail(id: String): Flow<Result<Story?>> =
        object :
            NetworkBoundProcessResource<Story?, com.fadhil.storyappexpert.core.data.source.remote.response.ApiResponse<com.fadhil.storyappexpert.core.data.source.remote.response.ResStory>?>() {

            override suspend fun createCall(): Result<com.fadhil.storyappexpert.core.data.source.remote.response.ApiResponse<com.fadhil.storyappexpert.core.data.source.remote.response.ResStory>?> {
                return remoteDataSource.getStoryDetail(id)
            }

            override suspend fun callBackResult(data: com.fadhil.storyappexpert.core.data.source.remote.response.ApiResponse<com.fadhil.storyappexpert.core.data.source.remote.response.ResStory>?): Story? {
                return data?.story?.let { mapper.mapStoryResponseToDomain(it) }
            }

        }.asFlow()

    override suspend fun addToFavorites(story: Story) =
        kotlinx.coroutines.withContext(Dispatchers.IO + NonCancellable) {
            val entity = mapper.mapStoryDomainToEntity(story)
            localDataSource.updateData(entity)
        }

    private fun uriToFile(imageUri: Uri, context: Context): File {
        val myFile = FileUtil.createCustomTempFile(context)
        val inputStream = context.contentResolver.openInputStream(imageUri) as InputStream
        val outputStream = FileOutputStream(myFile)
        val buffer = ByteArray(1024)
        var length: Int
        while (inputStream.read(buffer).also { length = it } > 0)
            outputStream.write(buffer, 0, length)
        outputStream.close()
        inputStream.close()
        return myFile
    }

    companion object {
        @Volatile
        private var instance: IStoryRepository? = null
        fun getInstance(
            remoteDataSource: com.fadhil.storyappexpert.core.data.source.remote.StoryRemoteDataSource,
            localDataSource: com.fadhil.storyappexpert.core.data.source.local.StoryLocalDataSource,
            storyRemoteMediator: StoryRemoteMediator,
            storyPagingSource: StoryPagingSource
        ): IStoryRepository =
            instance ?: synchronized(this) {
                instance ?: StoryRepository(
                    remoteDataSource,
                    localDataSource,
                    storyRemoteMediator,
                    storyPagingSource
                )
            }.also { instance = it }
    }

}