package com.example.labo9.MovieViewModel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.labo9.Entity.Movie
import com.example.labo9.Entity.MoviePreview
import com.example.labo9.MovieDatabase.MovieDB
import com.example.labo9.MovieRepository.MovieRespository
import com.example.labo9.services.ApiFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel (app: Application) : AndroidViewModel(app) {
    private val repository: MovieRespository

    init {
        val movieDao = MovieDB.getInstance(app).movieDao()
        repository = MovieRespository(movieDao, ApiFactory.ombdApi)
    }

    private val scope = CoroutineScope(Dispatchers.IO)

    private val movieslist = MutableLiveData<MutableList<MoviePreview>>()

    private val movieResult = MutableLiveData<Movie>()

    fun fetchMovie(name: String){
        scope.launch {
            val response=repository.retrieveMoviesByNameAsync(name).await()
            if(response.isSuccessful){
                when(response.code()){
                    200->movieslist.postValue(response.body()?.Search?.toMutableList()?:arrayListOf(MoviePreview(Title = "Dummy 1"), MoviePreview(Title = "Dummy 2")))
                }
            }else{
                Toast.makeText(app,, "Error", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun getMovieListVM(): LiveData<MutableList<MoviePreview>> = movieslist

    fun fetchMovieByTitle(name: String){
        scope.launch {
            val response=repository.retrieveMoviesByTitleAsync(name).await()
            if(response.isSuccessful) with(response){
                when(this.code()){
                    200->movieResult.postValue(this.body())
                }
            }else{
                Toast.makeText(app, "error", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun getMovieResult(): LiveData<Movie> = movieResult


    fun insert(movie: Movie) = scope.launch {
        repository.insert(movie)
    }

    fun getAll():LiveData<List<Movie>> = repository.getAllfromRoomDB()

    fun getMovieByName(name: String): LiveData<List<Movie>> = repository.getMovieByName(name)
}