package com.swetha.loginapp.network

import com.swetha.loginapp.model.LoginResponseDataModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {


    @POST("loginresponse")
    @FormUrlEncoded
    fun getLoginData(@Field("username") email: String,
                         @Field("password") password: String): Call<LoginResponseDataModel>}

