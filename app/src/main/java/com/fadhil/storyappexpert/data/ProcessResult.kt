package com.fadhil.storyapp.data

open class ProcessResult<T>(result: Result<T>, delegate: ProcessResultDelegate<T>?) {
    init {
        when(result.status) {
            Result.Status.LOADING -> delegate?.loading()
            Result.Status.ERROR -> {
                delegate?.error(result.code, result.message)
            }
            Result.Status.UNAUTHORIZED -> delegate?.unAuthorize(result.message)
            Result.Status.SUCCESS -> delegate?.success(result.data)
        }
    }
}