package com.example.moviedbapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedbapp.model.MovieModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.moviedbapp.util.ViewState
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieViewModel : ViewModel() {

    private val _state = MutableStateFlow<ViewState>(ViewState.Loading) //mutable state flow that holds the ViewState
    val state: StateFlow<ViewState> get() = _state //gets the state of the mutable state flow whenever its used

    /*
    searches for movies using the provided string parameter
    calls the getMovies() function in the model and sets the ViewState to success or error
     */
    fun movieSearch(title: String) {
        viewModelScope.launch { //launch coroutine
            val state = try {  //state is set to success or fail depending on if the trycatch block fails or not
                val movies = MovieModel.getMovies(title) //gets the movies by using the provided string as a search query
                ViewState.Success(movies) //sets the viewstate to success and passes the movies as a parameter
            } catch (ex: Exception) { //catches the try block if the getMovies call fails
                ViewState.Error(ex.message ?: "Catch Error") //sets the state to error and passes the error message
            }
            _state.value = state //sets the mutable state flow value as the state determined by the trycatch block
        }
    }
}

