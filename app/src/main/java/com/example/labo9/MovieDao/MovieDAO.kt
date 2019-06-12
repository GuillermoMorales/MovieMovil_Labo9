package com.example.labo9.MovieDao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.labo9.Entity.Movie

interface MovieDAO {

    @Query("select * from movie")
    fun getAllMovie(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie:Movie)

    @Query("delete from movie")
    suspend fun delete()

}