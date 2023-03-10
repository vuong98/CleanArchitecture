package com.trinityweareair.app.data.data_source.basenetwork

import android.util.Log
import com.trinityweareair.app.utils.ParserErrorApi.getMainMessageError


abstract class BaseService {
    protected  fun parseError(
        responseMessage : String?,
        responseCode : Int ,
        errorBody : String?
    ) : BaseNetworkException {
        val baseNetworkException = BaseNetworkException(responseMessage,responseCode)
        errorBody?.let {
            baseNetworkException.mainMessage = getMainMessageError(it, "message")
            baseNetworkException.parseFromString(it)
        }
        return baseNetworkException
    }

    protected fun parseNetworkErrorException(throwable: Throwable) : NetworkErrorException {
        return NetworkErrorException(throwable.message)
    }
}