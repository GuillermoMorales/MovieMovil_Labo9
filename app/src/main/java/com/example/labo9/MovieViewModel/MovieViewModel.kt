package com.example.labo9.MovieViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.labo9.Entity.Movie
import com.example.labo9.MovieDatabase.MovieDB
import com.example.labo9.MovieRepository.MovieRespository

class MovieViewModel (app: Application) : AndroidViewModel(app) {
    private val repository : MovieRespository
    val allMovies : LiveData<List<Movie>>

    init {
        var movieDao = MovieDB.getInstance(app,viewModelScope).movieDao()
        repository = MovieRespository(movieDao)
        allMovies = repository.getAllMovie()
    }
}