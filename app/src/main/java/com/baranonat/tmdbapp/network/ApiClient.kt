package com.baranonat.tmdbapp.network

import com.baranonat.tmdbapp.util.Constans
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    fun getClient():ApiService{
        val intercepter=HttpLoggingInterceptor()
        intercepter.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client:OkHttpClient=OkHttpClient.Builder().addInterceptor(intercepter)
            .connectTimeout(60,TimeUnit.SECONDS)
            .readTimeout(60,TimeUnit.SECONDS).build()

        return Retrofit.Builder().baseUrl(Constans.BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client).build().create(ApiService::class.java)
    }









}



