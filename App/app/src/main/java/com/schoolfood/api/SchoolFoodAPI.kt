package com.schoolfood.api

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface SchoolFoodAPI {
    @POST("balance") // making get request at marvel end-point
    fun getBalance(@Header("Authorization") auth: String): Call<Response?>?

    @POST("purchase") // making get request at marvel end-point
    fun placeOrder(@Header("Authorization") auth: String, @Body body: RequestBody): Call<Response?>?
}