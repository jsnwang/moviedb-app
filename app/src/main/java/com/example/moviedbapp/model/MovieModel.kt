package com.example.moviedbapp.model

import com.example.moviedbapp.model.remote.MovieService
import com.example.moviedbapp.model.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object MovieModel {

    //creates and holds the retrofit instance
    private val movieService by lazy {RetrofitInstance().movieService}

    /*
    gets list of movies
    String parameter to search for in the api query
    Using dispatchers IO thread coroutine
     */
    suspend fun getMovies (title: String) = withContext(Dispatchers.IO ){
        movieService.getMovies(MovieService.API_KEY, title)
    }
}