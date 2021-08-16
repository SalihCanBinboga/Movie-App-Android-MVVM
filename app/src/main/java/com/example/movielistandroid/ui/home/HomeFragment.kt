package com.example.movielistandroid.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.movielistandroid.data.models.MoviesResponseModel
import com.example.movielistandroid.databinding.FragmentHomeBinding
import com.example.movielistandroid.ui.home.adapters.UpComingMoviesAdapter
import com.example.movielistandroid.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val homeViewModel: HomeViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupUI()
        setupObservers()
        super.onViewCreated(view, savedInstanceState)
    }

    lateinit var upComingMoviesAdapter: UpComingMoviesAdapter

    private fun setupUI() {
        upComingMoviesAdapter = UpComingMoviesAdapter(null, requireContext())
        binding.upComingRecyclerView.apply {
            setHasFixedSize(true)
            adapter = upComingMoviesAdapter
        }
    }

    private fun setupObservers() {
        homeViewModel.getCurrentPlayingMovies().observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.upComingMoviesProgressBar.visibility = View.GONE
                        resource.data?.let { movieResponse -> updateSliderData(movieResponse = movieResponse) }
                    }
                    Status.ERROR -> {
                        binding.upComingMoviesProgressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.upComingMoviesProgressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
        homeViewModel.getUpComingMovies().observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { movieResponse -> updateUpComingMoviesList(movieResponse = movieResponse) }
                    }
                    Status.ERROR -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {}
                }
            }
        })
    }

    private fun updateUpComingMoviesList(movieResponse: MoviesResponseModel) {
        upComingMoviesAdapter.updateMoviesModel(moviesModel = movieResponse)
    }

    private fun updateSliderData(movieResponse: MoviesResponseModel) {}

}