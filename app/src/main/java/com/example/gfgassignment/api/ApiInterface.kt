package com.example.gfgassignment.api

import com.example.gfgassignment.models.Newslist
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("v1/api.json?rss_url=http://www.abc.net.au/news/feed/51120/rss.xml/")
    fun getArticles(): Call<Newslist>
}