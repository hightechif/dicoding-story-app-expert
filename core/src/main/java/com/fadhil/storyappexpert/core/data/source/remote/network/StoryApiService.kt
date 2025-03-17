package com.fadhil.storyappexpert.core.data.source.remote.network

interface StoryApiService {

    @retrofit2.http.POST("register")
    suspend fun register(@retrofit2.http.Body body: com.fadhil.storyappexpert.core.data.source.remote.request.ReqRegister): retrofit2.Response<com.fadhil.storyappexpert.core.data.source.remote.response.ApiResponse<Any?>?>

    @retrofit2.http.POST("login")
    suspend fun login(@retrofit2.http.Body body: com.fadhil.storyappexpert.core.data.source.remote.request.ReqLogin): retrofit2.Response<com.fadhil.storyappexpert.core.data.source.remote.response.ApiResponse<com.fadhil.storyappexpert.core.data.source.remote.response.ResLogin>?>

    @retrofit2.http.Multipart
    @retrofit2.http.POST("stories")
    suspend fun addNewStory(
        @retrofit2.http.Part file: okhttp3.MultipartBody.Part,
        @retrofit2.http.Part("description") description: okhttp3.RequestBody,
        @retrofit2.http.Part("lat") lat: okhttp3.RequestBody?,
        @retrofit2.http.Part("lon") lon: okhttp3.RequestBody?
    ): retrofit2.Response<com.fadhil.storyappexpert.core.data.source.remote.response.FileUploadResponse?>

    @retrofit2.http.Multipart
    @retrofit2.http.POST("stories/guest")
    suspend fun addNewStoryAsGuest(
        @retrofit2.http.Part file: okhttp3.MultipartBody.Part,
        @retrofit2.http.Part("description") description: okhttp3.RequestBody
    ): retrofit2.Response<com.fadhil.storyappexpert.core.data.source.remote.response.FileUploadResponse?>

    @retrofit2.http.GET("stories")
    suspend fun getAllStories(
        @retrofit2.http.Query("page") page: Int?,
        @retrofit2.http.Query("size") size: Int?,
        @retrofit2.http.Query("location") location: Int?
    ): retrofit2.Response<com.fadhil.storyappexpert.core.data.source.remote.response.ApiContentResponse<com.fadhil.storyappexpert.core.data.source.remote.response.ResStory>?>

    @retrofit2.http.GET("stories/{id}")
    suspend fun getStoryDetail(
        @retrofit2.http.Path("id") id: String,
    ): retrofit2.Response<com.fadhil.storyappexpert.core.data.source.remote.response.ApiResponse<com.fadhil.storyappexpert.core.data.source.remote.response.ResStory>?>

}