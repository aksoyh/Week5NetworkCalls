package com.aksoyh.week5networkcalls.utils

import com.aksoyh.week5networkcalls.utils.Status.*

sealed class Resource<T>(
        var data: T? = null,
        var message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String?, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}

/*
data class Resource<out T>(val status: Status, val data: T?, val message: String?) { // ? = nullable value
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(status = SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> = Resource(status = ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> = Resource(status = LOADING, data = data, message = null)
    }
}*
 */