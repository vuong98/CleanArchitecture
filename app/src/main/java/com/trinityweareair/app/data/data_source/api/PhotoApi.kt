package com.trinityweareair.app.data.data_source.api

import com.trinityweareair.app.domain.model.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {

//    @GET("photos")
//    suspend fun getAllListPhoto() :Response<Photo>
    @GET("weather")
    suspend fun getWeatherID(@Query("id") id : String) : Response<Photo>
}