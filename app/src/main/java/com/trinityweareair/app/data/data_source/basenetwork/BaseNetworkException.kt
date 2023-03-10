package com.trinityweareair.app.data.data_source.basenetwork

class BaseNetworkException(
    val responseMessage: String? = null,
    val responseCode: Int = -1,
    var mainMessage : String= ""
) : Exception() {

    fun parseFromString(errorBody: String) {

    }
}