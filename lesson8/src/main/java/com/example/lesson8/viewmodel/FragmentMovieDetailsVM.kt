package com.example.lesson8.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson8.data.MovieRepository
import com.example.lesson8.model.Failure
import com.example.lesson8.model.MovieDetails
import com.example.lesson8.model.Success
import kotlinx.coroutines.launch
import com.example.lesson8.data.MovieRepositoryImpl
import com.example.lesson8.data.db.entities.MovieDetailsEntity
import com.example.lesson8.model.Result

class FragmentMoviesDetailsVM : ViewModel() {
   lateinit var movieDetailsLiveData: LiveData<MovieDetailsEntity>

    fun loadDetails(movieId: Int) {
        movieDetailsLiveData = MovieRepositoryImpl.getLocalMovieDetails(movieId)
        viewModelScope.launch { MovieRepositoryImpl.loadMovieDetails(movieId) }
    }
}