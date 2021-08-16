package com.example.movielistandroid.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.movielistandroid.data.repositories.MoviesRepository
import com.example.movielistandroid.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    fun getMovie(movieID: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = moviesRepository.getMovie(movieID)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun getMovieSimilar(movieID: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = moviesRepository.getMovieSimilar(movieID)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}