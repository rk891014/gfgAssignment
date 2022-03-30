package com.example.gfgassignment.models

data class Newslist(
    val status: String,
    val feed: Feed,
    val items: List<Items>
)