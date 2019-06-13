package com.example.labo9.services

import com.example.labo9.Entity.Movie
import com.example.labo9.Entity.OmbdMovieResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val MOVIE_BASE_URL = "http://www.omdbapi.com/?apikey=5504e684&"
const val TOKEN_API = "8a05aef7"

interface MovieService {

    @GET("/")
    fun getMoviesByName(@Query("s") query: String): Deferred<Response<OmbdMovieResponse>>

    @GET("/")
    fun getMovieByTitle(@Query("t") query: String): Deferred<Response<Movie>>

    companion object{
        fun getMovieServices(): MovieService = Retrofit.Builder()
            .baseUrl(MOVIE_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(MovieService::class.java)

    }
}