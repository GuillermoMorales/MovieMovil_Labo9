package com.example.labo9.MovieRepository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.labo9.Entity.Movie
import com.example.labo9.Entity.OmbdMovieResponse
import com.example.labo9.MovieDao.MovieDAO
import com.example.labo9.services.MovieService
import kotlinx.coroutines.Deferred
import retrofit2.Response

class MovieRespository (private val movieDao:MovieDAO, private val api: MovieService) {

    fun retrieveMoviesByNameAsync(name:String):Deferred<Response<OmbdMovieResponse>> = api.getMoviesByName(name)

    fun retrieveMoviesByTitleAsync(name:String):Deferred<Response<Movie>> = api.getMovieByTitle(name)

    @WorkerThread
    suspend fun insert(movie: Movie) = movieDao.insertMovie(movie)

    fun getAllfromRoomDB():LiveData<List<Movie>> = movieDao.loadAllMovies()

    fun getMovieByName(name: String) = movieDao.searchMovieByName(name)

}