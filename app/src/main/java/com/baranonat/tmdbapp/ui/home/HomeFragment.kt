package com.baranonat.tmdbapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.baranonat.tmdbapp.R
import com.baranonat.tmdbapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding:FragmentHomeBinding?=null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ViewModelHome>()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding=FragmentHomeBinding.inflate(inflater,container,false)

        observeEvents()


        return binding.root
    }

    private fun observeEvents(){

        viewModel.errorMessage.observe(viewLifecycleOwner){error->
            binding.textViewHomeError.text=error
            binding.textViewHomeError.isVisible=true
        }
        viewModel.isLoading.observe(viewLifecycleOwner){loading->
            binding.progressBar.isVisible=loading

        }
        viewModel.movieList.observe(viewLifecycleOwner){list->
           if(list.isNullOrEmpty()){
               binding.textViewHomeError.text= "there is any movie"
               binding.textViewHomeError.isVisible=true
           }else{
               movieAdapter=MovieAdapter(list,object:MovieClickListener{
                   override fun onMovieClicked(movieId: Int?) {
                       movieId?.let {
                           val action=HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
                           findNavController().navigate(action)
                       }
                   }

               })
               binding.homeRecyclerView.adapter=movieAdapter
           }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}