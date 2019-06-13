package com.example.labo9.Constant

import android.util.Log
import com.example.labo9.BuildConfig
import com.example.labo9.Entity.Movie
import com.example.labo9.Entity.MoviePreview

object AppConstant {
    val ombdApiKey = BuildConfig.OMBD_API_KEY
    val ADD_TASK_REQUEST = 1
    val emptymoviespreview = ArrayList<MoviePreview>()
    val emptymovies = ArrayList<Movie>()

    fun debugPreviewMovies(result: List<Movie>){
        Log.d("PETROLERO", "__________________________________________________")
        Log.d("PETROLERO", "NEW MOVIES________________________________________")
        for (res in result) Log.d("PETROLERO", "${res.Title} -> id = ${res.imdbID}")
        Log.d("PETROLERO", "__________________________________________________")
    }
    fun debugPreviewMoviesPreview(result: List<MoviePreview>){
        Log.d("PETROLERO", "__________________________________________________")
        Log.d("PETROLERO", "PREVIEW___________________________________________")
        for (res in result) Log.d("PETROLERO", "${res.Title} -> selected = ${res.selected}")
        Log.d("PETROLERO", "__________________________________________________")
    }

}