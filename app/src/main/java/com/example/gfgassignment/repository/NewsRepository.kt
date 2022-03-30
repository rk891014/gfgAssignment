package com.example.gfgassignment.repository

import androidx.lifecycle.MutableLiveData
import com.example.gfgassignment.api.ApiInterface
import com.example.gfgassignment.api.RetrofitHelper
import com.example.gfgassignment.models.Newslist
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

object NewsRepository{

    val newslivedata = MutableLiveData<Newslist>()

    fun getnewsData() : MutableLiveData<Newslist>{
        val call = RetrofitHelper.getretrofit()
            .create(ApiInterface::class.java).getArticles()

        call.enqueue(object: Callback<Newslist> {
            override fun onFailure(call: Call<Newslist>, t: Throwable) {
                // TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<Newslist>, response: Response<Newslist>) {
                if(response?.body() != null){
                    val data = response.body()
                    newslivedata.value = data
                }

            }
        })

       return newslivedata
    }

}