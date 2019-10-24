package com.swetha.loginapp.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    var BASE_URL:String="https://sampleurl.com/"
    val getClient: ApiInterface
        get() {

            val gson = GsonBuilder()
                .setLenient()
                .create()
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(ApiInterface::class.java)

        }

}

//
//import com.swetha.loginapp.login.LoginResponse
//import com.squareup.moshi.Moshi
//import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
//import retrofit2.Call
//import retrofit2.Retrofit
//import retrofit2.converter.moshi.MoshiConverterFactory
//import retrofit2.http.GET
//
//private const val BASE_URL = "https://com.sample.com/"
//
//// TODO (02) Use Retrofit Builder with ScalarsConverterFactory and BASE_URL
//// TODO (03) Implement the MarsApiService interface with @GET getProperties returning a String
//// TODO (04) Create the MarsApi object using Retrofit to implement the MarsApiService
//
///**
// * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
// * full Kotlin compatibility.
// */
//private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()
//
///**
// * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
// * object.
// */
//private val retrofit = Retrofit.Builder()
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
//    .baseUrl(BASE_URL)
//    .build()
//
///**
// * A public interface that exposes the [getProperties] method
// */
//interface LoginApiService {
//    /**
//     * Returns a Retrofit callback that delivers a [List] of [MarsProperty]
//     * The @GET annotation indicates that the "realestate" endpoint will be requested with the GET
//     * HTTP method
//     */
//    @GET("LoginResponse")
//    fun getLoginResponse():
//            Call<List<LoginResponse>>
//}
//
///**
// * A public Api object that exposes the lazy-initialized Retrofit service
// */
//object LoginApi {
//    val retrofitService : LoginApiService by lazy { retrofit.create(LoginApiService::class.java) }
//}
/////**
//// * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
//// * object pointing to the desired URL
//// */
////private val retrofit = Retrofit.Builder()
////    .addConverterFactory(ScalarsConverterFactory.create())
////    .baseUrl(BASE_URL)
////    .build()
////
/////**
//// * A public interface that exposes the [getProperties] method
//// */
////interface APIService {
////    /**
////     * Returns a Retrofit callback that delivers a String
////     * The @GET annotation indicates that the "realestate" endpoint will be requested with the GET
////     * HTTP method
////     */
////    @GET("loginresponse")
////    fun getLoginResponse():
////            Call<String>
////}
////
/////**
//// * A public Api object that exposes the lazy-initialized Retrofit service
//// */
////object Api {
////    val retrofitService : APIService by lazy { retrofit.create(APIService::class.java) }
////}