package com.trinityweareair.app.utils

import android.util.Log

object ParserErrorApi {

    fun getMainMessageError(data : String, key : String)  :String {
        val index = data.indexOf(key)
        if (index == -1 ) {
            return data
        }else {
            return data.substring(index + key.length + 4 , data.length - 2);
        }
    }
}