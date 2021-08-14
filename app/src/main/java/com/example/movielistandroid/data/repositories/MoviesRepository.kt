package com.example.movielistandroid.data.repositories

import MoviesResponseModel
import com.example.movielistandroid.data.remote.MovieService

interface MoviesRepository {
    suspend fun getCurrentPlayingMovies(): MoviesResponseModel
    suspend fun getUpComingMovies(): MoviesResponseModel
}