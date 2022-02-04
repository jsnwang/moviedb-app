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
        _binding = FragmentHomeBinding.inflate(inflater, container, false) //infate the viewbinding
         //returns the root of the viewbinding which is the constraintlayout
        initViews()
        initObservers()

        return binding.root

    }

    private fun initViews() = with(binding){
        svSearchbar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.movieSearch(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun initObservers() = with(viewModel){
        lifecycleScope.launchWhenStarted{ //launch lifecycle coroutine
            state.collectLatest { state -> //collect the stateflow data
                binding.loader.isVisible = state is ViewState.Loading //Makes loader visible if ViewState is Loading
                if (state is ViewState.Success) handleSuccess(state.movie)
                if (state is ViewState.Error) handleError(state.error)
            }
        }
    }

    private fun handleSuccess(movie: Movie) {

    }

    private fun handleError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }


}