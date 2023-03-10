package com.trinityweareair.app.data.data_source.basenetwork

import android.util.Log
import com.google.gson.Gson
import com.trinityweareair.app.domain.model.error.errorMessage
import com.trinityweareair.app.utils.ParserErrorApi.getMainMessageError
import retrofit2.Response

open class BaseRemoteService  : BaseService(){
    protected suspend fun <T : Any> callApi(call: suspend () -> Response<T>) : NetworkResult<T> {
        val response : Response<T>
        try {
            response = call.invoke()
        }
        catch (t : Throwable) {
            return NetworkResult.Error(parseNetworkErrorException(t))
        }
        return if (response.isSuccessful) {
            if (response.body() == null) {
                NetworkResult.Error(
                    BaseNetworkException(
                        responseMessage = "Response without body",
                        responseCode = 200
                    )
                )
            }else {
                NetworkResult.Sucess(response.body()!!)
            }
        }else {

            val errorBody = response.errorBody()?.string() ?:""

            NetworkResult.Error(parseError(response.message(), response.code(), errorBody))
        }
    }
}