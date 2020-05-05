package com.htueko.android.demots.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity(tableName = "now_playing_table")
data class MovieModel(
    @PrimaryKey
    @NotNull
    @SerializedName("id") val id: Long,
    @NotNull
    @SerializedName("title") val title: String,
    @NotNull
    @SerializedName("overview") val overview: String,
    @NotNull
    @SerializedName("poster_path") val posterPath: String,
    @NotNull
    @SerializedName("backdrop_path") val backdropPath: String,
    @NotNull
    @SerializedName("vote_average") val rating: Float,
    @NotNull
    @SerializedName("release_date") val releaseDate: String
)

data class GetNowPlayingResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<MovieModel>,
    @SerializedName("total_pages") val pages: Int
)
