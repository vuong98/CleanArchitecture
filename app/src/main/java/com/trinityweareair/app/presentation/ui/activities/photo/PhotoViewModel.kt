package com.trinityweareair.app.presentation.ui.activities.photo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.trinityweareair.app.data.data_source.basenetwork.BaseNetworkException
import com.trinityweareair.app.data.data_source.basenetwork.NetworkErrorException
import com.trinityweareair.app.data.data_source.basenetwork.NetworkResult
import com.trinityweareair.app.domain.model.Photo
import com.trinityweareair.app.domain.usecase.PhotoUsecase
import com.trinityweareair.app.presentation.ui.base.BaseViewModel.BaseViewModel
import com.trinityweareair.app.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(val photoUsercase: PhotoUsecase) : BaseViewModel() {

    var livedataPhoto = MutableLiveData<Photo>()
    val _livedataPhoto: LiveData<Photo> = livedataPhoto

    fun getListPhoto(id: String) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val listPhoto = photoUsercase.photoRepository.getAllListPhoto(id)
            delay(2000)
            listPhoto.collectLatest {
                when (it) {
                    is NetworkResult.Sucess -> {
                        livedataPhoto.value = it.data!!
                    }
                    is NetworkResult.Error -> {
                        if (it.exception is BaseNetworkException) {
                            baseNetworkException.value = SingleEvent(
                                BaseNetworkException(
                                    it.exception.responseMessage,
                                    it.exception.responseCode, it.exception.mainMessage
                                )
                            )
                        } else {
                            networkException.value =
                                SingleEvent(NetworkErrorException(it.exception.toString()))
                        }
                    }
                }
            }
        }
        registerJobFinish()
    }
}