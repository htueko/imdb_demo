package com.htueko.android.demots.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.htueko.android.demots.data.model.MovieModel

@Dao
interface MovieDAO {

    // to get all the movies list in now playing
    @Query("select * from now_playing_table")
    suspend fun getAllMovies(): List<MovieModel>

    // to insert movies to database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(model: List<MovieModel>)

    // to get the movie with id
    @Query(value = "select * from now_playing_table where id = :id")
    suspend fun getMovieWithId(id: Long): MovieModel?

    // to get all the id of the movies
    @Query(value = "select id from now_playing_table")
    suspend fun getAllIds(): List<Long>

    // to get all the movies with id
    @Query("select * from now_playing_table where id in (:id)")
    suspend fun getAllMoviesWithIds(id: List<Long>): List<MovieModel>

    // to remove from favourite list
    @Query("delete from now_playing_table where id in (:id)")
    suspend fun deleteFromFavourite(id: Long)

//    @Query("insert into now_playing_table (nowPlayingList) values (:id)")
//    suspend fun insertIntoNowPlayingList(id: Long)
//
//    @Query("insert into now_playing_table (upComingList) values (:id)")
//    suspend fun insertIntoUpComingList(id: Long)
//
//    @Query("insert into now_playing_table (favouriteList) values (:id)")
//    suspend fun insertIntoFavouriteList(id: Long)

}