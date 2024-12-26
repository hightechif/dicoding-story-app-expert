package com.fadhil.storyappexpert.core.data

interface ProcessResultDelegate<T> {
    fun loading()
    fun error(code: String?, message: String?)
    fun unAuthorize(message: String?)
    fun success(data: T?)
}