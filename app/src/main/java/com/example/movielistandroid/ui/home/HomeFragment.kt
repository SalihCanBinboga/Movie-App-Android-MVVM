package com.example.movielistandroid.ui.home

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.movielistandroid.R
import com.example.movielistandroid.data.models.MoviesResponseModel
import com.example.movielistandroid.databinding.FragmentHomeBinding
import com.example.movielistandroid.ui.home.adapters.NowPlayingSliderAdapter
import com.example.movielistandroid.ui.home.adapters.UpComingMoviesAdapter
import com.example.movielistandroid.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    lateinit var upComingMoviesAdapter: UpComingMoviesAdapter
    lateinit var nowPlayingSliderAdapter: NowPlayingSliderAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupUI()
        setupObservers()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupUI() {
        upComingMoviesAdapter = UpComingMoviesAdapter(null, requireContext())
        binding.upComingRecyclerView.apply {
            setHasFixedSize(true)
            adapter = upComingMoviesAdapter
        }
        nowPlayingSliderAdapter = NowPlayingSliderAdapter(null, requireContext())
        binding.nowPlayingSlider.apply {
            adapter = nowPlayingSliderAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    addIndicatorView(position)
                }
            })
        }

    }

    private fun setupObservers() {
        homeViewModel.getCurrentPlayingMovies().observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.moviesProgressBar.visibility = View.GONE
                        resource.data?.let { movieResponse -> updateSliderData(movieResponse = movieResponse) }
                    }
                    Status.ERROR -> {
                        binding.moviesProgressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.moviesProgressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
        homeViewModel.getUpComingMovies().observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.moviesProgressBar.visibility = View.GONE
                        resource.data?.let { movieResponse -> updateUpComingMoviesList(movieResponse = movieResponse) }
                    }
                    Status.ERROR -> {
                        binding.moviesProgressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.moviesProgressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun updateUpComingMoviesList(movieResponse: MoviesResponseModel) {
        upComingMoviesAdapter.updateMoviesModel(moviesModel = movieResponse)
    }

    private fun updateSliderData(movieResponse: MoviesResponseModel) {
        nowPlayingSliderAdapter.updateMoviesModel(moviesModel = movieResponse)

        if (movieResponse.results.isNotEmpty()) {

            lifecycleScope.launch {
                while (true) {
                    for (i in 0..movieResponse.results.size) {
                        delay(1500)
                        if (i == 0) {
                            binding.nowPlayingSlider.setCurrentItem(i, false)
                        } else {
                            binding.nowPlayingSlider.setCurrentItem(i, true)
                        }
                    }
                }
            }
        }

    }

    private fun addIndicatorView(currentPage: Int) {
        binding.tabLayoutIndCatorLayout.removeAllViews()

        nowPlayingSliderAdapter.moviesModel?.let {
            println("bum bir ${it.results.size}")
            for (i in 0..it.results.size) {
                println("bum iki ${it.results.size}")
                val indicator = TextView(requireContext())

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    indicator.text = Html.fromHtml("&#8226", Html.FROM_HTML_MODE_LEGACY)
                } else {
                    indicator.text = Html.fromHtml("&#8226")
                }

                indicator.textSize = 38f
                if (currentPage == i) {
                    indicator.setTextColor(ContextCompat.getColor(requireContext(), R.color.white_30))
                } else {
                    indicator.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                }

                binding.tabLayoutIndCatorLayout.addView(indicator)
            }
        }

    }

}