package com.htueko.android.demots.repository

import android.content.Context
import com.htueko.android.demots.data.database.MyDatabase
import com.htueko.android.demots.data.model.MovieModel

class MainRepository private constructor(context: Context){

    companion object {
        private var INSTANCE: MainRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = MainRepository(context)
            }
        }

        fun get(): MainRepository {
            return INSTANCE ?:
            throw IllegalStateException("MainRepository must be initialized")
        }
    }

    private val mDatabase = MyDatabase.getInstance(context)
    private val movieDAO = mDatabase.nowPlayingDAO

    suspend fun getAllMovies(): List<MovieModel> = movieDAO.getAllMovies()
    suspend fun insertAllMovies(list: List<MovieModel>) = movieDAO.insertAllMovies(list)
    suspend fun getMovieWithId(id: Long) = movieDAO.getMovieWithId(id)
    suspend fun getAllIds() = movieDAO.getAllIds()
    suspend fun getAllMoviesWithIds(id: List<Long>) = movieDAO.getAllMoviesWithIds(id)


}