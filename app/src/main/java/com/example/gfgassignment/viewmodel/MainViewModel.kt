package com.example.gfgassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gfgassignment.models.Newslist
import com.example.gfgassignment.repository.NewsRepository

class MainViewModel : ViewModel(){

//    mutablelivedata(newslivedata) for storing data from repository

    var newsLiveData : MutableLiveData<Newslist>? = null

    fun getNews() : LiveData<Newslist>? {
        newsLiveData = NewsRepository.getnewsData()
        return newsLiveData
    }
}