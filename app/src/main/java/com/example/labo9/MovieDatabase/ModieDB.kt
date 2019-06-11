package com.example.labo9.MovieDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.labo9.Entity.Movie
import com.example.labo9.MovieDao.MovieDAO
import kotlinx.coroutines.CoroutineScope
import okhttp3.internal.Internal.instance



@Database(entities = [Movie::class],version = 1,exportSchema = false)
abstract class MovieDB : RoomDatabase(){
    abstract fun movieDao(): MovieDAO

    companion object {
        @Volatile
        private var INSTANCE: MovieDB? = null

        fun getInstance(context: Context, scope: CoroutineScope): MovieDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context, MovieDB::class.java, "MovieDB")
                    .fallbackToDestructiveMigration()
//                    .addCallback(RoomDBCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }

        }

    }

}