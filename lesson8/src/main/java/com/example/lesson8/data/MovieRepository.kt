package com.example.lesson8.data
import android.annotation.SuppressLint
import android.content.Context
import com.example.lesson8.App
import com.example.lesson8.api.RetrofitInstance.api
import com.example.lesson8.api.ImageUrlAppender.Size
import com.example.lesson8.api.RetrofitInstance.imageUrlAppender
import com.example.lesson8.data.db.entities.MovieDetailsEntity
import com.example.lesson8.data.response.CastResponse
import com.example.lesson8.data.response.MovieCastResponse
import com.example.lesson8.data.response.MovieDetailsResponse
import com.example.lesson8.data.response.MovieResponse
import com.example.lesson8.model.*
import kotlinx.coroutines.Dispatchers

interface MovieRepository {

    suspend fun loadMovies()
    suspend fun loadMovieDetails(movieId: Int)
}

private const val ADULT_AGE = 16
private const val CHILD_AGE = 13

@SuppressLint("StaticFieldLeak")
object MovieRepositoryImpl : MovieRepository {

    private lateinit var db: MoviesDatabase
    val localMovies by lazy(Dispatchers.IO) { db.moviesDao().getAllMovies() }
    private val localGenres by lazy(Dispatchers.IO) { db.moviesDao().getAllGenres() }
    override suspend fun loadMovies() {
        val result = runCatchingResult { getMovies() }

        if (result is Success) {
            val movies = result.data
            db.moviesDao().insertMovies(movies.map { it.toMovieEntity() })
        }
    }

    private suspend fun getMovies(): List<Movie> {
        loadGenres()
        return api.getUpcomingMovies(page = 1).results.map { movie ->
            movieResponseToMovie(movie, localGenres.value?.map { it.toGenre() } ?: emptyList())
        }
    }

    private suspend fun loadGenres() {
        val genres = api.getGenres().genres.map { it.toGenreEntity() }
        db.moviesDao().insertGenres(genres)
    }

    override suspend fun loadMovieDetails(movieId: Int) {
        val result = runCatchingResult { getMovieDetails(movieId) }

        if (result is Success) {
            db.moviesDao().insertMovieDetails(result.data)
        }
    }



    fun createDatabase(context: Context) {
        db = MoviesDatabase.getInstance(context)
    }

    private suspend fun getMovieDetails(movieId: Int): MovieDetailsEntity {
        val details = api.getMovieDetails(movieId)
        val actors = api.getMovieCredit(movieId)

        return detailsResponseToMovieDetailsEntity(details, actors)
    }

    fun getLocalMovieDetails(id: Int) = db.moviesDao().getMovieDetails(id)
}


private fun Boolean.toSpectatorAge(): Int = if (this) ADULT_AGE else CHILD_AGE

suspend fun movieResponseToMovie(movie: MovieResponse, genres: List<Genre>) = Movie(
    id = movie.id,
    title = movie.title,
    imageUrl = imageUrlAppender.getImageUrl(movie.posterPath, Size.POSTER),
    rating = movie.voteAverage / 2,
    reviewCount = movie.voteCount,
    pgAge = movie.adult.toSpectatorAge(),
    runningTime = 100,
    isLiked = false,
    genres = genres.filter { genre -> movie.genreIds.contains(genre.id) }
)

suspend fun actorResponseToActorEntity(actor: CastResponse) = Actor(
        id = actor.id,
        name = actor.name,
        imageUrl = imageUrlAppender.getImageUrl(actor.profilePath ?: "",Size.PROFILE)
        )

suspend fun detailsResponseToMovieDetailsEntity(
    details: MovieDetailsResponse,
    actors: MovieCastResponse
) = MovieDetailsEntity(
    id = details.id,
    pgAge = details.adult.toSpectatorAge(),
    title = details.title,
    genres = App.gson.toJson(details.genres),
    reviewCount = details.revenue,
    isLiked = false,
    rating = details.voteAverage / 2,
    detailImageUrl = imageUrlAppender.getImageUrl(
        details.backdropPath ?: "",
        Size.BACKDROP
    ),
    storyLine = details.overview.orEmpty(),
    actors = App.gson.toJson(actors.casts.map { actorResponseToActorEntity(it) })
)