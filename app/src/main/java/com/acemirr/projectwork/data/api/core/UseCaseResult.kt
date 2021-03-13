package com.acemirr.projectwork.data.api.core

sealed class UseCaseResult<out T : Any> {
    class Success<out T : Any>(val data: T) : UseCaseResult<T>()
    class Error(val exception: Throwable) : UseCaseResult<Nothing>()
    class Failed(val errorMessage: String) : UseCaseResult<Nothing>()
}