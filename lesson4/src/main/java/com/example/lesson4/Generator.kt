package com.example.lesson4

import android.content.Context

fun generateActors(): List<Actor> {

    val ironMan = Actor(
        "Robert Downey Jr.",
        R.drawable.img_movie_cast_1
    )

    val captainAmerica = Actor(
        "Chris Evans",
        R.drawable.img_movie_cast_2
    )

    val hulk = Actor(
        "Mark Ruffalo",
        R.drawable.img_movie_cast_3
    )

    val thor = Actor(
        "Chris Hemsworth",
        R.drawable.img_movie_cast_4
    )

    return listOf(ironMan, captainAmerica, hulk, thor)
}

fun generateMovies(context: Context): List<Movie> {

    val movie = Movie(
        context.getString(R.string.film_name), context.getString(R.string.age),context.getString(R.string.storyline_text), 4f,
        196, context.getString(R.string.genres),140, true, R.drawable.img_movies_item_header_avengers
    )

    val movie2 = Movie(
        context.getString(R.string.film_name), context.getString(R.string.age),context.getString(R.string.storyline_text), 4f,
        196, context.getString(R.string.genres), 140, true, R.drawable.img_movies_item_header_avengers
    )

    val movie3 = Movie(
        context.getString(R.string.film_name), context.getString(R.string.age),context.getString(R.string.storyline_text), 4f,
        196, context.getString(R.string.genres), 140, true, R.drawable.img_movies_item_header_avengers
    )

    val movie4 = Movie(
        context.getString(R.string.film_name), context.getString(R.string.age),context.getString(R.string.storyline_text), 4f,
        196, context.getString(R.string.genres), 140, true, R.drawable.img_movies_item_header_avengers
    )

    return listOf(movie, movie2, movie3, movie4)
}