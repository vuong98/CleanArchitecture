package com.trinityweareair.app.data.data_source.basenetwork

sealed class NetworkResult<out T: Any> {
    data class Sucess<out T:Any>(val data : T) : NetworkResult<T>()
    data class Error(val exception: Exception) : NetworkResult<Nothing>()
}