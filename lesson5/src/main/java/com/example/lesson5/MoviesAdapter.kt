package com.example.lesson5

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
import com.example.lesson4.R

class MoviesAdapter(
    private val context: Context,
    private val listener: OnMovieListener
) : RecyclerView.Adapter<MoviesAdapter.ViewHolderMovie>() {

    private var movies: List<Movie> = listOf()
    private val inflater: LayoutInflater = LayoutInflater.from(context)


    fun update(newList: List<Movie>) {
        movies = newList
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): Movie = movies[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMovie {
        return ViewHolderMovie(inflater.inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderMovie, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolderMovie(v: View) : RecyclerView.ViewHolder(v) {
        private val image: ImageView = itemView.findViewById(R.id.iv_banner)
        private val genres: TextView = itemView.findViewById(R.id.tv_film_desc)
        private val name: TextView = itemView.findViewById(R.id.tv_film_name_text)
        private val age: TextView = itemView.findViewById(R.id.tv_film_number)
        private val duration: TextView = itemView.findViewById(R.id.tv_film_time_text)
        private val numReviews: TextView = itemView.findViewById(R.id.tv_film_time_text)
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
            genres.text = movie.genres
            name.text = movie.name
            age.text = movie.age
            duration.text = movie.duration.toString().plus(" MIN")
            numReviews.text = movie.numReviews.toString().plus(" REVIEWS")
            rating.rating = movie.rating
            favorite.setColorFilter(
                if (movie.isFavorite) {
                    ContextCompat.getColor(context,
                        R.color.pink
                    )
                } else {
                    ContextCompat.getColor(context,
                        R.color.white
                    )
                }
            )
            image.setImageResource(movie.image)

        }

    }

    interface OnMovieListener {
        fun onClickMovie(movie: Movie)
    }
}


