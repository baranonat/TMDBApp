package com.baranonat.tmdbapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.baranonat.tmdbapp.databinding.ItemHomeRecyclerViewBinding
import com.baranonat.tmdbapp.model.MovieItem
import com.baranonat.tmdbapp.util.loadCircleImage

interface MovieClickListener{

    fun onMovieClicked(movieId:Int?)
}
class MovieAdapter(val movieList:List<MovieItem?>,private val movieClickListener: MovieClickListener):RecyclerView.Adapter<MovieAdapter.MovieHolder>() {
    class MovieHolder(val binding:ItemHomeRecyclerViewBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding=ItemHomeRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
       return MovieHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie=movieList[position]
        holder.binding.textViewMovieTitle.text=movie?.title
        holder.binding.textViewMovieOverview.text=movie?.overview
        holder.binding.textViewMovieVote.text=movie?.voteAverage.toString()

        holder.binding.imageViewMovie.loadCircleImage(movie?.posterPath)

       holder.binding.root.setOnClickListener{
           movieClickListener.onMovieClicked(movieId = movie?.id)
       }

    }
}