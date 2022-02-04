package com.example.moviedbapp.model.resource


import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("Response")
    val response: String,
    @SerializedName("Search")
    val search: List<Search>,
    val totalResults: String
)