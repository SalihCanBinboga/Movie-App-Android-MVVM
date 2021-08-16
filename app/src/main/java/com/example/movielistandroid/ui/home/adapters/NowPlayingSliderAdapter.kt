package com.example.movielistandroid.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielistandroid.data.models.MoviesResponseModel
import com.example.movielistandroid.databinding.ItemSliderBinding
import com.example.movielistandroid.databinding.ItemUpComingListBinding

class NowPlayingSliderAdapter(private var moviesModel: MoviesResponseModel?, private val mContext: Context) : RecyclerView.Adapter<NowPlayingSliderAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        moviesModel?.let {
            val model = it.results[position]
            val view = holder.binding

            view.sliderTitleTextView.text = model.original_title
            view.sliderSubTitleTextView.text = model.overview

            Glide.with(mContext)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${model.poster_path}")
                .into(view.movieImageView)
        }

    }

    fun updateMoviesModel(moviesModel: MoviesResponseModel){
        this.moviesModel = moviesModel
        notifyDataSetChanged()
    }


    class ViewHolder(val binding: ItemSliderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = moviesModel?.results?.size ?: 0
}