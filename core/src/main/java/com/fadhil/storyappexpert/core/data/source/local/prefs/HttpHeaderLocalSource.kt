package com.fadhil.storyappexpert.core.data.source.local.prefs

import android.content.SharedPreferences
import com.google.gson.Gson

class HttpHeaderLocalSource(sharedPreferences: SharedPreferences):
    LocalDataSource<MutableMap<String, String?>>(sharedPreferences) {
    private var headers: MutableMap<String, String?>? = null

    override fun getKeyName(): String = "HttpHeader"
    override fun getValue(json: String): MutableMap<String, String?> =
        com.google.gson.Gson()
            .fromJson(json, object : com.google.gson.reflect.TypeToken<MutableMap<String, String?>>() {}.type)

    fun setHeader(key: String, value: String?) {
        if (headers == null) {
            headers = getCached()
        }

        if (headers == null) {
            headers = mutableMapOf()
        }

        if (value == null) {
            headers?.remove(key)
        }
        else {
            headers?.put(key, value)
        }
        save(headers)
    }

    fun setBearerToken(token: String) {
        val accessToken = String.format("Bearer %s", token)
        setHeader("Authorization", accessToken)
    }

    fun isLogged(): Boolean{
        if (headers == null) {
            headers = getCached()
        }

        return headers?.get("Authorization") != null
    }

    fun logout() {
        if (headers == null) {
            headers = getCached()
        }

        headers?.remove("Authorization")
        save(headers)
    }
}