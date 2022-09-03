package com.example.wanted_preonboarding_android.utils

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Suscces<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}