package com.baranonat.tmdbapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baranonat.tmdbapp.model.MovieDetailResponse
import com.baranonat.tmdbapp.network.ApiClient
import com.baranonat.tmdbapp.network.ApiService
import com.baranonat.tmdbapp.util.Constans
import kotlinx.coroutines.launch

class ViewModelDetail : ViewModel() {


    val movieResponse: MutableLiveData<MovieDetailResponse> = MutableLiveData()
    val isLoading = MutableLiveData(false)
    val errorMessage: MutableLiveData<String> = MutableLiveData()


    fun getMovieDetail(movieId: Int) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val response = ApiClient.getClient()
                    .getMovieDetail(movieId = movieId.toString(), Constans.BEARER_TOKEN)
                if (response.isSuccessful) {
                    movieResponse.postValue(response.body())
                } else {
                    if (response.message().isNullOrEmpty()) {
                        errorMessage.value = "An unknown error occurred"
                    } else {
                        errorMessage.value = response.message()
                    }
                }
            } catch (e: Exception) {
                errorMessage.value = e.message
            } finally {                isLoading.value = false
            }

        }
    }


}