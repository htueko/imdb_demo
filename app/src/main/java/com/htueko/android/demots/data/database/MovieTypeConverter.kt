package com.htueko.android.demots.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class MovieTypeConverter {

    @TypeConverter
    fun fromInt(num: Int): Long{
        return num.toLong()
    }

    @TypeConverter
    fun fromLong(num: Long): Int{
        return num.toInt()
    }

    @TypeConverter
    fun fromLongListToIntList(value: List<Long>): List<Int>{
        return value.map { it.toInt() }
    }

    @TypeConverter
    fun fromIntListToLongList(value: List<Int>): List<Long>{
        return value.map { it.toLong() }
    }

    @TypeConverter
    fun fromStringToLong(value: List<String>): List<Long>{
        return value.map { it.toLong() }
    }

    @TypeConverter
    fun fromLongToString(value: List<Long>): List<String>{
        return value.map { it.toString() }
    }


}