package com.example.lesson4

import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class FragmentMoviesDetails (private var movie: Movie) : Fragment(R.layout.fragment_movies_details) {
    private val tvAge: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_back) }
    private val tvFilm: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_movie_name) }
    private val tvGenre: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_movie_tags) }
    private val rating: RatingBar by lazy { requireView().findViewById<RatingBar>(R.id.rating) }
    private val tvNumReviews: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_movie_reviews_count) }
    private val tvStoryLne: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_movie_storyline) }
    private val actorsAdapter: ActorListAdapter by lazy {
        ActorListAdapter(requireContext()).apply {
            setList(generateActors())
        }
    }

    private val rvActors: RecyclerView by lazy {
        requireView().findViewById<RecyclerView>(R.id.rv_actor).apply {

            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }

    private val tvBack: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_back) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        rvActors.adapter = actorsAdapter
    }
}
