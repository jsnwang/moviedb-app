package com.example.moviedbapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbapp.databinding.FragmentHomeBinding
import com.example.moviedbapp.databinding.MovieItemBinding
import com.example.moviedbapp.model.resource.Search

class MovieAdapter (private val movieSearch : List<Search>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    //instantiates the viewbinding when the view is created
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        = MovieViewHolder.newInstance(parent)

    //displays data at specified position
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int)
        = holder.bindMovie(movieSearch[position])

    //returns the total number of items in the recyclerview
    override fun getItemCount()
        = movieSearch.size




    //viewholder for recyclerview adapter
   class MovieViewHolder (private val binding: MovieItemBinding)
       : RecyclerView.ViewHolder(binding.root) {
        //func that runs for each item in the recyclerview
           fun bindMovie (movie: Search) = with(binding) {
                tvTitle.text = movie.title
           }

        //instantiates and inflates the viewbinding
       companion object{
           fun newInstance(parent: ViewGroup) = MovieItemBinding.inflate(
               LayoutInflater.from(parent.context), parent, false)
               .let { MovieViewHolder(it) }

       }
       }

}