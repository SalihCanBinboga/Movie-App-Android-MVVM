package com.example.movielistandroid.data.repositories

import com.example.movielistandroid.data.models.MoviesResponseModel
import com.example.movielistandroid.data.models.Results


interface MoviesRepository {
    suspend fun getCurrentPlayingMovies(): MoviesResponseModel
    suspend fun getUpComingMovies(): MoviesResponseModel
    suspend fun getMovie(movieID: String): Results
    suspend fun getMovieSimilar(movieID: String): MoviesResponseModel
}