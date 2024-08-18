package com.baranonat.tmdbapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels

import androidx.navigation.fragment.navArgs
import com.baranonat.tmdbapp.MainActivity
import com.baranonat.tmdbapp.R
import com.baranonat.tmdbapp.databinding.FragmentDetailBinding
import com.baranonat.tmdbapp.databinding.FragmentHomeBinding
import com.baranonat.tmdbapp.util.loadImage


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ViewModelDetail>()

    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)


        viewModel.getMovieDetail(args.movieId)
        observeEvents()

        return binding.root

    }

    private fun observeEvents() {

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBarDetail.isVisible = it
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            binding.textViewErrorDetail.text = it
            binding.textViewErrorDetail.isVisible = true
        }
        viewModel.movieResponse.observe(viewLifecycleOwner) { movie ->
            binding.imageViewDetail.loadImage(movie.backdropPath)



            binding.textViewDetailVote.text = movie.voteAverage.toString()
            binding.textViewDetailStudio.text = movie.productionCompanies?.first()?.name
            binding.textViewDetailLanguage.text = movie.spokenLanguages?.first()?.englishName


            binding.textViewDetailOverview.text = movie.overview

            binding.movieDetailGroup.isVisible = true

            (requireContext() as MainActivity).supportActionBar?.title=movie.title


        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}