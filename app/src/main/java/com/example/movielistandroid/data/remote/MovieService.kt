package com.example.movielistandroid.data.remote

import MoviesResponseModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("3/movie/now_playing")
    fun getCurrentPlayingMovies(@Query("api_key") apiKey: String): Call<MoviesResponseModel>


    @GET("3/movie/upcoming")
    fun getUpComingMovies(@Query("api_key") apiKey: String): Call<MoviesResponseModel>


    companion object {
        const val MOVIE_API_KEY = "85743f73c2cc3c332a656d70d9663b5c"

        var retrofitService: MovieService? = null

        fun getInstance() : MovieService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(MovieService::class.java)
            }
            return retrofitService!!
        }
    }
}