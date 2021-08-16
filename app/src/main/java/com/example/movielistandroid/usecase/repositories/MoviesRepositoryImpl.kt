package com.example.movielistandroid.usecase.repositories

import com.example.movielistandroid.data.remote.MovieService
import com.example.movielistandroid.data.remote.MovieService.Companion.MOVIE_API_KEY
import com.example.movielistandroid.data.repositories.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl
@Inject constructor(
    private val movieService: MovieService
) : MoviesRepository {

    override suspend fun getCurrentPlayingMovies() = movieService.getCurrentPlayingMovies(apiKey = MOVIE_API_KEY)
    override suspend fun getUpComingMovies() = movieService.getUpComingMovies(apiKey = MOVIE_API_KEY);
}