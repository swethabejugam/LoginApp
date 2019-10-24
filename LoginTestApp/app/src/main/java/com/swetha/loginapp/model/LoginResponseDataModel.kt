package com.swetha.loginapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponseDataModel(

    @Expose
    @SerializedName("statusCode")
    val statusCode: Integer,
    @Expose
    @SerializedName("error")
    val error: String,
    @Expose
    @SerializedName("token")
    val token: String
    )