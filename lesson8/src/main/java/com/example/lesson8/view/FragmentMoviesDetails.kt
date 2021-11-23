package com.example.lesson8.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.lesson8.constants.MOVIE
import com.example.lesson8.R
import com.example.lesson8.adapters.ActorListAdapter
import com.example.lesson8.model.Movie
import com.example.lesson8.model.MovieDetails
import com.example.lesson8.viewmodel.FragmentMoviesDetailsVM


class FragmentMoviesDetails : Fragment(R.layout.fragment_movies_details) {
    private val fragmentDetailsVM: FragmentMoviesDetailsVM by lazy {
        ViewModelProvider(this).get(FragmentMoviesDetailsVM::class.java)
    }
    private val tvAge: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_desc) }
    private val tvFilm: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_movie_name) }
    private val tvGenre: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_movie_tags) }
    private val rating: RatingBar by lazy { requireView().findViewById<RatingBar>(R.id.rating) }
    private val tvNumReviews: TextView by lazy { requireView().findViewById<TextView>(
        R.id.tv_movie_reviews_count
    ) }
    private val tvStoryLne: TextView by lazy { requireView().findViewById<TextView>(
        R.id.tv_movie_storyline
    ) }
    private val tvBack: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_back) }
    private val image: ImageView by lazy { requireView().findViewById<ImageView>(R.id.iv_top_background) }
    private val actorsAdapter: ActorListAdapter by lazy { ActorListAdapter(requireContext()) }

    private val rvActors: RecyclerView by lazy {
        requireView().findViewById<RecyclerView>(R.id.rv_actor).apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getInt(FragmentMoviesList.MOVIE_ID) ?: 0

        fragmentDetailsVM.loadDetails(id)

        tvBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        fragmentDetailsVM.movieDetailsLiveData.observe(this, {
            it?.run {
                val movie = it.toMovieDetails()
                setViews(movie )
                setGlide(movie)
                setAdapter(movie)
            }
        })
    }

    private fun setViews(movie: MovieDetails) {
        tvAge.text = movie.pgAge.toString().plus("+")
        tvFilm.text = movie.title
        tvGenre.text = movie.genres.joinToString(separator = ", ") { it.name }
        rating.rating = movie.rating
        tvStoryLne.text = movie.storyLine
        tvNumReviews.text = movie.reviewCount.toString().plus(" REVIEWS")
    }

    private fun setGlide(movie: MovieDetails) {
        val requestOptions = RequestOptions().apply {
            transform(CenterCrop(), RoundedCorners(16))
        }

        Glide.with(requireContext())
            .load(movie.detailImageUrl)
            .apply(requestOptions)
            .into(image)
    }

    private fun setAdapter(movie: MovieDetails) {
        actorsAdapter.setList(movie.actors)
        rvActors.adapter = actorsAdapter
    }

}