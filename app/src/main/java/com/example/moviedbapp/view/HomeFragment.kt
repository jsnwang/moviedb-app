package com.example.moviedbapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.moviedbapp.adapter.MovieAdapter
import com.example.moviedbapp.databinding.FragmentHomeBinding
import com.example.moviedbapp.model.resource.Movie
import com.example.moviedbapp.util.ViewState
import com.example.moviedbapp.viewmodel.MovieViewModel
import kotlinx.coroutines.flow.collectLatest

class HomeFragment : Fragment(){
    private var _binding : FragmentHomeBinding ?= null //viewbinding of home fragment
    private val binding get() = _binding!! //gets the above value
    private val viewModel by viewModels<MovieViewModel>() //get viewmodel using kotlin property delegate

    //is called when the view is first initialized
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        //inflate the viewbinding
        _binding = FragmentHomeBinding.inflate(inflater, container, false) //infate the viewbinding

        initViews()
        initObservers()


        return binding.root //returns the root of the viewbinding which is the constraintlayout

    }

    //initialize the views, in this case its only the search bar listener
    private fun initViews() = with(binding){

        //search bar listener listens for activity in the search bar
        svSearchbar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            //runs when the query text is submitted
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.movieSearch(query!!) //searches for movies based on the query provided
                return true
            }

            //unused listener that is run everytime any text is changed in the input field
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }


    private fun initObservers() = with(viewModel){
        lifecycleScope.launchWhenStarted{ //launch lifecycle coroutine
            state.collectLatest { state -> //collect the stateflow data
                Log.d("state", state.toString())
                binding.loader.isVisible = state is ViewState.Loading //Makes loader visible if ViewState is Loading
                if (state is ViewState.Success) handleSuccess(state.movie)
                //when the api responds successfully set the ViewState to Success and pass the movies
                if (state is ViewState.Error) handleError(state.error)
                //if the api encounters an error set the ViewState to Error and pass the error message
            }
        }
    }

    //runs when the ViewState is success
    private fun handleSuccess(movie: Movie) {
        Log.d("movies", movie.toString())
        //passes the movie data to the recyclerview adapter
        binding.rvMovies.adapter = MovieAdapter(movie.search)
    }

    //runs when the ViewState is error
    private fun handleError(error: String) {
        //shows the error in a toast
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }


}