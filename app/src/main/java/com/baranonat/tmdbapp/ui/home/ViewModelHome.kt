package com.baranonat.tmdbapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baranonat.tmdbapp.model.MovieItem
import com.baranonat.tmdbapp.network.ApiClient
import com.baranonat.tmdbapp.util.Constans
import kotlinx.coroutines.launch


class ViewModelHome:ViewModel() {


    val movieList:MutableLiveData<List<MovieItem?>?> = MutableLiveData()
    val isLoading=MutableLiveData(false)
    val errorMessage:MutableLiveData<String?> = MutableLiveData()

    init{
        getMovieList()
    }

    fun getMovieList(){
        isLoading.value=true
        viewModelScope.launch {
            try{

                val response = ApiClient.getClient().getMovieList(token=Constans.BEARER_TOKEN)
                if(response.isSuccessful){
                    movieList.postValue(response.body()?.movieItems)
                }else{
                    if(response.message().isNullOrEmpty()){
                        errorMessage.value = "An unknown error occurred"
                    }else{
                        errorMessage.value=response.message()
                    }
                }
            }catch (e:Exception){
                    errorMessage.value=e.message
            }finally {
        isLoading.value=false
            }

        }
    }



}