package com.example.movielistandroid.data.remote

import com.example.movielistandroid.data.models.MoviesResponseModel
import com.example.movielistandroid.data.models.Results
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("3/movie/now_playing")
    suspend fun getCurrentPlayingMovies(@Query("api_key") apiKey: String): MoviesResponseModel


    @GET("3/movie/upcoming")
    suspend fun getUpComingMovies(@Query("api_key") apiKey: String): MoviesResponseModel

    @GET("3/movie/{movieID}")
    suspend fun getMovie(
        @Path("movieID") movieID: String,
        @Query("api_key") apiKey: String
    ): Results


    @GET("3/movie/{movieID}/similar")
    suspend fun getMovieSimilar(
        @Path("movieID") movieID: String,
        @Query("api_key") apiKey: String
    ): MoviesResponseModel
}