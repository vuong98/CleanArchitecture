package com.trinityweareair.app.domain.usecase

import com.trinityweareair.app.data.data_source.basenetwork.NetworkResult
import com.trinityweareair.app.domain.model.Photo
import com.trinityweareair.app.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoUsecase @Inject constructor(val photoRepository : PhotoRepository) {

    suspend operator fun invoke(id : String) : Flow<NetworkResult<Photo>> {
        return photoRepository.getAllListPhoto(id);
    }
}