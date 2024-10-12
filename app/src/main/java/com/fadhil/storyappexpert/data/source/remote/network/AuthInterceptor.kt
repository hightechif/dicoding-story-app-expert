package com.fadhil.storyappexpert.data.source.remote.network

import com.fadhil.storyappexpert.data.source.local.prefs.HttpHeaderLocalSource
import com.fadhil.storyappexpert.util.StringUtil.Empty
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class AuthInterceptor(
    private val httpHeaderLocalSource: HttpHeaderLocalSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val headers = httpHeaderLocalSource.getCached()
        val accessToken = headers?.get("Authorization") ?: String.Empty
        Timber.d("token=$accessToken")
        val requestBuilder = chain.request().newBuilder()
        val request = if (accessToken.isNotEmpty()) {
            requestBuilder
                .addHeader("Authorization", accessToken)
                .build()
        } else requestBuilder.build()
        return chain.proceed(request)
    }

}