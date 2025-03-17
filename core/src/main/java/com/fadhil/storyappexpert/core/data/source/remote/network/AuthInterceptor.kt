package com.fadhil.storyappexpert.core.data.source.remote.network

import com.fadhil.storyappexpert.core.data.source.local.prefs.HttpHeaderLocalSource
import com.fadhil.storyappexpert.core.util.StringUtil.Empty
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val httpHeaderLocalSource: HttpHeaderLocalSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val headers = httpHeaderLocalSource.getCached()
        val accessToken = headers?.get("Authorization") ?: String.Empty
        val requestBuilder = chain.request().newBuilder()
        val request = if (accessToken.isNotEmpty()) {
            requestBuilder
                .addHeader("Authorization", accessToken)
                .build()
        } else requestBuilder.build()
        return chain.proceed(request)
    }

}