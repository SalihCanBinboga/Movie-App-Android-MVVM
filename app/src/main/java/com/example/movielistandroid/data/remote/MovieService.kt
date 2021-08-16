package com.example.movielistandroid.data.remote

import com.example.movielistandroid.data.models.MoviesResponseModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("3/movie/now_playing")
    suspend fun getCurrentPlayingMovies(@Query("api_key") apiKey: String): MoviesResponseModel


    @GET("3/movie/upcoming")
    suspend fun getUpComingMovies(@Query("api_key") apiKey: String): MoviesResponseModel


    companion object {
        const val MOVIE_API_KEY = "85743f73c2cc3c332a656d70d9663b5c"

        var movieService: MovieService? = null

        fun getInstance(): MovieService {
            if (movieService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                movieService = retrofit.create(MovieService::class.java)
            }
            return movieService!!
        }
    }
}