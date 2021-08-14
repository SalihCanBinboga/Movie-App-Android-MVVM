package com.example.movielistandroid.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface  MovieService {

    @GET("3/movie/now_playing")
    fun getCurrentPlayingMovies(@Query("api_key") apiKey: String): Call<String>


    @GET("3/movie/upcoming")
    fun getUpComingMovies(@Query("api_key") apiKey: String): Call<String>
}