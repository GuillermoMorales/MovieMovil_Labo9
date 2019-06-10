package com.example.labo9.services

import com.example.labo9.Entity.Movie
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val MOVIE_BASE_URL = "http://www.omdbapi.com/?apikey=5504e684&"

interface MovieService {

    @GET("")
    fun getMovie(@retrofit2.http.Query("t") query:String): Deferred<Response<Movie>>

    companion object{
        fun getMovieServices(): MovieService = Retrofit.Builder()
            .baseUrl(MOVIE_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(MovieService::class.java)

    }
}