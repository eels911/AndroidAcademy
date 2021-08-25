package com.example.lesson5.data.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GenresResponse(
    @SerializedName("genres") val genres: List<GenreResponse>,
): Serializable