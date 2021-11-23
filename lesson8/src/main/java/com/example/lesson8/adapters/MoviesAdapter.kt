package com.example.lesson8.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.lesson8.R
import com.example.lesson8.model.Movie

class MoviesAdapter(
    private val context: Context,
    private val listener: OnMovieListener
) : RecyclerView.Adapter<MoviesAdapter.ViewHolderMovie>() {

    private var movies: List<Movie> = listOf()

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private fun getItem(position: Int): Movie = movies[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMovie {
        return ViewHolderMovie(inflater.inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderMovie, position: Int) {
        holder.bind(getItem(position))
    }

    fun refresh(moviesList: List<Movie>) {
        movies = moviesList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolderMovie(v: View) : RecyclerView.ViewHolder(v) {
        private val image: ImageView = itemView.findViewById(R.id.iv_banner)
        private val genres: TextView = itemView.findViewById(R.id.tv_film_desc)
        private val name: TextView = itemView.findViewById(R.id.tv_film_name_text)
        private val age: TextView = itemView.findViewById(R.id.tv_film_number)
        private val duration: TextView = itemView.findViewById(R.id.tv_film_time_text)
        private val numReviews: TextView = itemView.findViewById(R.id.tv_movie_reviews_count)
        private val rating: RatingBar = itemView.findViewById(R.id.rating)
        private val favorite: ImageView = itemView.findViewById(R.id.iv_favorite_icon)
        val clickListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                val pos = adapterPosition
                Log.d("---------", "Click")

                if (pos != RecyclerView.NO_POSITION) {
                    listener.onClickMovie(getItem(pos))
                }
            }
        }

        init {
            itemView.setOnClickListener(clickListener)
        }

        fun bind(movie: Movie) {
            setViews(movie)

            val requestOptions = RequestOptions().apply {
                transform(CenterCrop(), RoundedCorners(16))
            }
            Glide.with(image.context)
                .load(movie.imageUrl)
                .apply(requestOptions)
                .into(image)
        }

        private fun setViews(movie: com.example.lesson8.model.Movie) {
            genres.text = movie.genres.joinToString(separator = ", ") { it.name }
            name.text = movie.title
            age.text = movie.pgAge.toString().plus("+")
            duration.text = movie.runningTime.toString().plus(" MIN")
            numReviews.text = movie.reviewCount.toString().plus("K REVIEWS")
            rating.rating = movie.rating
            favorite.setColorFilter(
                if (movie.isLiked) {
                    ContextCompat.getColor(context, R.color.pink)
                } else {
                    ContextCompat.getColor(context, R.color.white)
                }
            )
        }
    }

    interface OnMovieListener {
        fun onClickMovie(movie: Movie)
    }
}