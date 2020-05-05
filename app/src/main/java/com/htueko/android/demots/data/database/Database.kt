package com.htueko.android.demots.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.htueko.android.demots.data.model.MovieModel

@Database(
    entities = [MovieModel::class],
    version = 1,
    exportSchema = false
)
//@TypeConverters(MovieTypeConverter::class)
abstract class MyDatabase : RoomDatabase(){
    abstract val nowPlayingDAO: MovieDAO

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            synchronized(this) {
                var instance = INSTANCE
                // .allowMainThreadQueries()
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "movie_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }

}