package com.fadhil.storyappexpert.core.data.source.remote.network

import java.util.concurrent.TimeUnit

object StoryApi {

    private const val BASE_URL = "https://story-api.dicoding.dev/v1/"

    fun buildLoggerInterceptor(): okhttp3.logging.HttpLoggingInterceptor {
        val loggerInterceptor = okhttp3.logging.HttpLoggingInterceptor()
        loggerInterceptor.setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.BODY)
        return loggerInterceptor
    }

    fun buildAuthInterceptor(httpHeaderLocalSource: com.fadhil.storyappexpert.core.data.source.local.prefs.HttpHeaderLocalSource): AuthInterceptor {
        return AuthInterceptor(httpHeaderLocalSource)
    }

    fun buildClient(
        loggerInterceptor: okhttp3.logging.HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        isDebug: Boolean
    ): okhttp3.OkHttpClient =
        if (isDebug) {
            okhttp3.OkHttpClient.Builder()
                .addInterceptor(loggerInterceptor)
                .addInterceptor(authInterceptor)
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build()
        } else {
            okhttp3.OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build()
        }


    /**
     * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
     * object.
     */
    fun buildRetrofit(client: okhttp3.OkHttpClient): retrofit2.Retrofit =
        retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()

    /**
     * A public Api object that exposes the lazy-initialized Retrofit service
     */
    fun buildApiService(retrofit: retrofit2.Retrofit): StoryApiService {
        val service: StoryApiService by lazy { retrofit.create(StoryApiService::class.java) }
        return service
    }

}