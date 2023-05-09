package com.schoolfood.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {
    private var REST_CLIENT: SchoolFoodAPI? = null

    //if REST_CLIENT is null then set-up again.
    val instance: SchoolFoodAPI?
        get() {
            //if REST_CLIENT is null then set-up again.
            if (REST_CLIENT == null) {
                setupRestClient()
            }

            return REST_CLIENT
        }

    private fun setupRestClient() {
        var restAdapter = Retrofit.Builder()
            .baseUrl("https://codermerlin.academy/vapor/brett-kaplan/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        REST_CLIENT = restAdapter.create(SchoolFoodAPI::class.java)
    }
}