package com.htueko.android.demots.data.api

import android.util.Log
import com.htueko.android.demots.data.model.GetNowPlayingResponse
import com.htueko.android.demots.data.model.MovieModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiRepository {

    interface Api {

        @GET("movie/upcoming")
        fun getUpComingMovies(
            @Query("api_key") apiKey: String = API_KEY
        ): Call<GetNowPlayingResponse>

        @GET("movie/now_playing")
        fun getNowPlayingMovies(
            @Query("api_key") apiKey: String = API_KEY
        ): Call<GetNowPlayingResponse>

    }

    private val api: Api
    private const val API_KEY = "d3355b2fb458c9c45b7beddd5786ec79"

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(Api::class.java)
    }

    fun getUpComingMovies(onSuccess: (movies: List<MovieModel>) -> Unit, onError: () -> Unit){
        api.getUpComingMovies(API_KEY)
            .enqueue(object : Callback<GetNowPlayingResponse>{
                override fun onFailure(call: Call<GetNowPlayingResponse>, t: Throwable) {
                    onError.invoke()
                }

                override fun onResponse(
                    call: Call<GetNowPlayingResponse>,
                    response: Response<GetNowPlayingResponse>
                ) {
                    if (response.isSuccessful){
                        val responseBody = response.body()
                        if (responseBody != null){
                            val list = responseBody.movies
                            list.forEach {
                                Log.d("koko*^*^*^*^*^", "$it\n")
                            }
                            onSuccess.invoke(responseBody.movies)
                        }else{
                            onError.invoke()
                        }
                    }
                }

            })
    }

    fun fetchNowPlayingMovies(): List<MovieModel>{
        val list : MutableList<MovieModel> = mutableListOf()
        api.getNowPlayingMovies(API_KEY)
            .enqueue(object : Callback<GetNowPlayingResponse>{
                override fun onFailure(call: Call<GetNowPlayingResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<GetNowPlayingResponse>,
                    response: Response<GetNowPlayingResponse>
                ) {
                    if (response.isSuccessful){
                        val responseBody = response.body()
                        if (responseBody != null){
                            responseBody.movies.forEach { item ->
                                list.add(item)
                            }
                        }else{

                        }
                    }
                }
            })
        return list
    }

    fun getNowPlayingMovies(onSuccess: (movies: List<MovieModel>) -> Unit, onError: () -> Unit) {
        api.getNowPlayingMovies(API_KEY)
            .enqueue(object : Callback<GetNowPlayingResponse>{
                override fun onFailure(call: Call<GetNowPlayingResponse>, t: Throwable) {
                    onError.invoke()
                }

                override fun onResponse(
                    call: Call<GetNowPlayingResponse>,
                    response: Response<GetNowPlayingResponse>
                ) {
                    if (response.isSuccessful){
                        val responseBody = response.body()
                        if (responseBody != null){
                            val list = responseBody.movies
                            list.forEach {
                                Log.d("koko-----------", "$it\n")
                            }
                            onSuccess.invoke(responseBody.movies)
                        }else{
                            onError.invoke()
                        }
                    }
                }
            })
    }

}