package com.example.gfgassignment.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    const val BASE_URL = "https://api.rss2json.com/"

//    func to convert JSON data got from server into java model
    fun getretrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}


