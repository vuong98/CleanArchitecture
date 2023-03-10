package com.trinityweareair.app.domain.model.error

import com.google.gson.annotations.SerializedName

data class errorMessage(
    @SerializedName("cod")
    val cod : Int? ,
    @SerializedName("message")
    val message: String?,
):java.io.Serializable