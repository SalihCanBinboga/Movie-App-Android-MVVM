package com.example.movielistandroid.data.repositories

import com.example.movielistandroid.data.models.MoviesResponseModel


interface MoviesRepository {
    suspend fun getCurrentPlayingMovies(): MoviesResponseModel
    suspend fun getUpComingMovies(): MoviesResponseModel
}