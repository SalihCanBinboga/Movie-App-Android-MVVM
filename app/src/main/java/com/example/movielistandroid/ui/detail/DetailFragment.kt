package com.example.movielistandroid.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.movielistandroid.R
import com.example.movielistandroid.data.models.Results
import com.example.movielistandroid.databinding.FragmentDetailBinding
import com.example.movielistandroid.databinding.FragmentHomeBinding
import com.example.movielistandroid.ui.home.HomeViewModel
import com.example.movielistandroid.utils.Status

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val detailViewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupObservers()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupObservers() {
        detailViewModel.getMovie(movieID = args.movieID).observe(viewLifecycleOwner,{
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.movieProgressBar.visibility = View.GONE
                        resource.data?.let { movieResponse -> updateUI(movieResponse = movieResponse) }
                    }
                    Status.ERROR -> {
                        binding.movieProgressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.movieProgressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun updateUI(movieResponse: Results) {

    }
}