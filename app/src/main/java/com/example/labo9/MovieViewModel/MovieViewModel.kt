package com.example.labo9.MovieViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.labo9.Entity.Movie
import com.example.labo9.MovieDatabase.ModieDB
import com.example.labo9.MovieRepository.MovieRespository

class MovieViewModel (app: Application) : AndroidViewModel(app) {
    private val repository : MovieRespository
    val allMovies : LiveData<List<Movie>>

    init {
        val movieDao = ModieDB.getInstance(app).movieDao()
        repository = MovieRespository(movieDao)
        movieDao = repository.allMovies
    }
}