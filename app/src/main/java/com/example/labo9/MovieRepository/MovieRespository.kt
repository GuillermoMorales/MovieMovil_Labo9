package com.example.labo9.MovieRepository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.labo9.Entity.Movie
import com.example.labo9.MovieDao.MovieDAO
import com.example.labo9.services.MovieService
import kotlinx.coroutines.Deferred
import retrofit2.Response

class MovieRespository (private val movieDao:MovieDAO) {

    fun retrieveMovieAsync(title: String): Deferred<Response<Movie>> =
        MovieService.getMovieServices().getMovie(title)

    @WorkerThread
    suspend fun insert(movie:Movie){
        movieDao.insert(movie)
    }

    fun getMovie(): LiveData<Movie>{
        return movieDao.getMovie()
    }

    @WorkerThread
    suspend fun delete(){
        return movieDao.delete()
    }

}