package com.example.moviedbapp.util

import com.example.moviedbapp.model.resource.Movie

sealed class ViewState { //sealed class so it cannot be inherited


    object Loading : ViewState() //loading state when the api has not given a response yet

    data class Success(val movie: Movie) : ViewState() //success state when the api gives a successful response

    data class Error(val error: String) : ViewState() //error state when the api call encounters an error
}