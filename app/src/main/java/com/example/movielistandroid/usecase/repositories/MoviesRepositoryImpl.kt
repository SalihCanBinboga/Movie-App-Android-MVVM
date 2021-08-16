package com.example.movielistandroid.usecase.repositories

import com.example.movielistandroid.data.remote.MovieService
import com.example.movielistandroid.data.repositories.MoviesRepository
import com.example.movielistandroid.utils.Constants
import javax.inject.Inject

class MoviesRepositoryImpl
@Inject constructor(
    private val movieService: MovieService
) : MoviesRepository {
    override suspend fun getCurrentPlayingMovies() = movieService.getCurrentPlayingMovies(apiKey = Constants.API_KEY)
    override suspend fun getUpComingMovies() = movieService.getUpComingMovies(apiKey = Constants.API_KEY);
    override suspend fun getMovie(movieID: String) = movieService.getMovie(movieID = movieID, apiKey = Constants.API_KEY);
    override suspend fun getMovieSimilar(movieID: String) = movieService.getMovieSimilar(movieID = movieID, apiKey = Constants.API_KEY)
}