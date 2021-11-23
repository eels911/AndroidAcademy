package com.example.lesson8.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lesson8.model.MovieDetails
import com.example.lesson8.App.Companion.generatorActor
import com.example.lesson8.App.Companion.generatorGenre
import com.example.lesson8.App.Companion.gson
import java.io.Serializable

@Entity(tableName = "movies_details_table")
data class MovieDetailsEntity(
    @PrimaryKey
    val id: Int,
    val pgAge: Int,
    val title: String,
    val genres: String,
    val reviewCount: Int,
    val isLiked: Boolean,
    val rating: Float,
    val detailImageUrl: String?,
    val storyLine: String,
    val actors: String
): Serializable {
    fun toMovieDetails() = MovieDetails(
        id = id,
        pgAge = pgAge,
        title = title,
        genres = gson.fromJson(genres, generatorGenre),
        reviewCount = reviewCount,
        isLiked = false,
        rating = rating,
        detailImageUrl = detailImageUrl,
        storyLine = storyLine,
        actors = gson.fromJson(actors, generatorActor)
    )
}