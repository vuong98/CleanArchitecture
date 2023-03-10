package com.trinityweareair.app.domain.repository

import com.trinityweareair.app.data.data_source.basenetwork.NetworkResult
import com.trinityweareair.app.domain.model.Photo
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
//    suspend fun getAllListPhoto() : Flow<NetworkResult<Photo>>
        suspend fun getAllListPhoto(id : String) : Flow<NetworkResult<Photo>>
}