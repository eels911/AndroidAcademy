package com.example.lesson4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MoviesAdapter(
    private val context: Context,
    var movies: List<Movie>,
    private val listener: OnMovieListener
) : RecyclerView.Adapter<MoviesAdapter.ViewHolderMovie>() {


    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private fun getItem(position: Int): Movie = movies[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMovie {
        return ViewHolderMovie(inflater.inflate(R.layout.view_holder_movie,parent, false))
    }
    override fun onBindViewHolder(holder: ViewHolderMovie, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = movies.size

    class ViewHolderMovie(v : View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private val image: ImageView = itemView.findViewById(R.id.iv_movie_1_list)
        private val genres: TextView = itemView.findViewById(R.id.tv_genre)
        private val name: TextView = itemView.findViewById(R.id.tv_film_name)
        private val age: TextView = itemView.findViewById(R.id.tv_age)
        private val duration: TextView = itemView.findViewById(R.id.tv_duration)
        private val numReviews: TextView = itemView.findViewById(R.id.tv_num_reviews)
        private val rating: RatingBar = itemView.findViewById(R.id.rating)
        private val favorite: ImageView = itemView.findViewById(R.id.iv_like)}

    interface OnMovieListener {
        fun onClickMovie(movie: Movie)
    }
}