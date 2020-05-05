package com.htueko.android.demots.repository

import android.content.Context
import com.htueko.android.demots.data.database.MyDatabase
import com.htueko.android.demots.data.model.MovieModel

class MovieRepository private constructor(context: Context){

    companion object {
        private var INSTANCE: MovieRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = MovieRepository(context)
            }
        }

        fun get(): MovieRepository {
            return INSTANCE ?:
            throw IllegalStateException("NowPlayingRepository must be initialized")
        }
    }

    private val mDatabase = MyDatabase.getInstance(context)
    private val nowPlayingDAO = mDatabase.nowPlayingDAO

    suspend fun getNowPlayingMovies(): List<MovieModel> = nowPlayingDAO.getAllMovies()
    suspend fun insertNowPlayingMovies(list: List<MovieModel>) = nowPlayingDAO.insertAllMovies(list)

}