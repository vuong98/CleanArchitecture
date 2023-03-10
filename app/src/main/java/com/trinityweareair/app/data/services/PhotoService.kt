package com.trinityweareair.app.data.services

import com.trinityweareair.app.data.data_source.api.PhotoApi
import com.trinityweareair.app.data.data_source.basenetwork.BaseRemoteService
import com.trinityweareair.app.data.data_source.basenetwork.NetworkResult
import com.trinityweareair.app.domain.model.Photo
import javax.inject.Inject

class PhotoService @Inject constructor(private val photoApi : PhotoApi) : BaseRemoteService() {
//    suspend fun getAllListPhoto() : NetworkResult<Photo> {
//        return callApi {  photoApi.getAllListPhoto() }
//    }
    suspend fun getWeatherID(id : String) : NetworkResult<Photo> {
        return callApi { photoApi.getWeatherID(id) }
    }
}