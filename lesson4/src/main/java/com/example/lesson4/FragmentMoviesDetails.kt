package com.example.lesson4

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment


class FragmentMoviesDetails (private var movie: Movie) : Fragment(R.layout.fragment_movies_details) {
    private val tvAge: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_back) }
    private val tvFilm: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_film) }
    private val tvGenre: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_genre) }
    private val rating: RatingBar by lazy { requireView().findViewById<RatingBar>(R.id.rating) }
    private val tvNumReviews: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_num_reviews) }
    private val tvStoryLne: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_storyline_text) }
    private val actorsAdapter: ActorListAdapter by lazy {
        ActorListAdapter(requireContext()).apply {
            setList(generateActors())
        }
    var listener: BackClickListener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is BackClickListener) {
            listener = context
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.tv_back)?.setOnClickListener {
            listener?.onMovieDeselected()

        }
    }
    interface BackClickListener {
        fun onMovieDeselected()
    }

}
