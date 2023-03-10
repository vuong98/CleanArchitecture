package com.trinityweareair.app.presentation.ui.base.BaseViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trinityweareair.app.data.data_source.basenetwork.BaseNetworkException
import com.trinityweareair.app.data.data_source.basenetwork.NetworkErrorException
import com.trinityweareair.app.utils.SingleEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job

open class BaseViewModel : ViewModel() {
    var baseNetworkException = MutableLiveData<SingleEvent<BaseNetworkException>>()
        protected set

    var networkException = MutableLiveData<SingleEvent<NetworkErrorException>>()
        protected set

    var isLoading = MutableLiveData<SingleEvent<Boolean>>()
        protected set

    var onNavigateToPage = MutableLiveData<SingleEvent<Int>>()
        protected set

    var errorMessageResourceId = MutableLiveData<SingleEvent<Int>>()
        protected set

    var notifyMessageResourceId = MutableLiveData<SingleEvent<Int>>()
        protected set

    var isLoadingMore = MutableLiveData<SingleEvent<Boolean>>()
        protected set

    var parentJob: Job? = null
        protected set
    protected fun registerJobFinish(){
        parentJob?.invokeOnCompletion {
            showLoading(false)
        }
    }

    val handler = CoroutineExceptionHandler { _, exception ->
        parseErrorCallApi(exception)
    }

    protected fun showError(messageId: Int) {
        errorMessageResourceId.postValue(SingleEvent(messageId))
    }

    protected fun showNotify(messageId: Int) {
        notifyMessageResourceId.postValue(SingleEvent(messageId))
    }

    protected fun addBaseNetworkException(exception: BaseNetworkException) {
        baseNetworkException.postValue(SingleEvent(exception))
    }

    protected fun addNetworkException(exception: NetworkErrorException) {
        networkException.postValue(SingleEvent(exception))
    }

    protected fun showLoading(isShow: Boolean) {
        Log.d("baseViewModelShowLoading: ", "VAO")
        isLoading.postValue(SingleEvent(isShow))
    }

    protected fun showLoadingMore(isShow: Boolean){
        isLoadingMore.postValue(SingleEvent(isShow))

    }

    protected fun navigateToPage(actionId: Int) {
        onNavigateToPage.postValue(SingleEvent(actionId))
    }

    protected open fun parseErrorCallApi(e: Throwable) {
        when (e) {
            is BaseNetworkException -> {
                baseNetworkException.postValue(SingleEvent(e))
            }
            is NetworkErrorException -> {
                networkException.postValue(SingleEvent(e))
            }
            else -> {
                val unknowException = BaseNetworkException()
                unknowException.mainMessage = e.message ?: "Something went wrong"
                baseNetworkException.postValue(SingleEvent(unknowException))
            }
        }
    }
}