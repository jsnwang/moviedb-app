package com.example.moviedbapp.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitInstance {
    private val retrofit = Retrofit.Builder()

        .baseUrl("http://www.omdbapi.com/") //base url for the api call
        .addConverterFactory(GsonConverterFactory.create()) //converts the json data into a java class
        .build() //creates the retrofit instance

    val movieService: MovieService by lazy {
        retrofit.create()
    }
}