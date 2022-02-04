package com.example.moviedbapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.moviedbapp.databinding.FragmentHomeBinding
import com.example.moviedbapp.viewmodel.MovieViewModel

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
        return binding.root
        initViews()
        initObservers()

    }

    private fun initViews() = with(binding){

    }

    private fun initObservers() = with(viewModel){

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }


}