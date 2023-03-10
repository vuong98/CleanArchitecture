package com.trinityweareair.app.data.data_source.basenetwork


object NetworkHelper {

    fun getDefaultHeader() : Map<String, String> {
        val headers = mutableMapOf<String, String>()
        headers["Content-type"] = "application/json"
        return headers.toMap()
    }
    fun getDefaultHeaderForCustomer() : Map<String, String> {
        val headers = mutableMapOf<String, String>()
        headers["Content-type"] = "application/json"
        return headers.toMap()
    }
}