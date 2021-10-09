package com.example.lesson7.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson7.model.Failure
import com.example.lesson7.model.MovieDetails
import com.example.lesson7.model.Success
import kotlinx.coroutines.launch
import com.example.lesson7.data.MovieRepositoryImpl
import com.example.lesson7.model.Result

class FragmentMoviesDetailsVM : ViewModel() {
    var movieDetailsLiveData: MutableLiveData<MovieDetails> = MutableLiveData()
        private set
    fun loadDetails(movieId: Int) {
        viewModelScope.launch {
            val movie = MovieRepositoryImpl.loadMovieDetails(movieId)

            handleResult(movie)
        }
    }

    private fun handleResult(result: Result<MovieDetails?>) {
        when (result) {
            is Success -> handleMovieLoadResult(result.data)
            is Failure -> movieDetailsLiveData.postValue(
                MovieDetails(
                    0, 0, "",
                    emptyList(), 0, false, 0.0f, null, "",
                    emptyList()
                )
            )
        }
    }

    private fun handleMovieLoadResult(movie: MovieDetails?) {

        movieDetailsLiveData.postValue(
            movie ?: MovieDetails(
                0, 0, "",
                emptyList(), 0, false, 0.0f, null, "",
                emptyList()
            )
        )

    }

}