package com.trinityweareair.app.data.repository

import com.trinityweareair.app.data.data_source.basenetwork.NetworkResult
import com.trinityweareair.app.domain.model.Photo
import com.trinityweareair.app.data.services.PhotoService
import com.trinityweareair.app.domain.repository.PhotoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val photoService: PhotoService,
    private val dispatcher: CoroutineDispatcher
) : PhotoRepository {
    override suspend fun getAllListPhoto(id : String): Flow<NetworkResult<Photo>> = flow {
       // Log.d ("getAllListPhoto: ", "${Thread.currentThread().name}")
        try {
//            val result = photoService.getAllListPhoto()
            val result = photoService.getWeatherID(id);
            emit(result)
        } catch (e: Exception) {
            emit(NetworkResult.Error(e))
        }
    }.flowOn(dispatcher)
}