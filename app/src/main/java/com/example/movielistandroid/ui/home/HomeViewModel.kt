package com.example.movielistandroid.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.movielistandroid.data.repositories.MoviesRepository
import com.example.movielistandroid.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

}