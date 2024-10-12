package com.fadhil.storyappexpert.data

interface ProcessResultDelegate<T> {
    fun loading()
    fun error(code: String?, message: String?)
    fun unAuthorize(message: String?)
    fun success(data: T?)
}