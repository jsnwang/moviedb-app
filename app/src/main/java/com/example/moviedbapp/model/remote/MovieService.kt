package com.example.moviedbapp.model.remote

import com.example.moviedbapp.model.resource.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    companion object{
        const val BASE_URL = "http://www.omdbapi.com/"
        const val API_KEY = "2c4cd2cb" //api key needed for api calls
    }

    @GET //GET request to retrieve data from the api

    /* suspend function to make the api call for movie searches
       has to run in a coroutine
    */
    suspend fun getMovies (
        @Query("apikey") key : String, //api key query required for api calls
        @Query("t") title : String //title query to search for
    ) : Movie //returns Movie data class


}