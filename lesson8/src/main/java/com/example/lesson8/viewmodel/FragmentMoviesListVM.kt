package com.example.lesson8.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson8.data.MovieRepository

import com.example.lesson8.data.MovieRepositoryImpl
import com.example.lesson8.data.db.entities.MovieEntity
import com.example.lesson8.model.*
import kotlinx.coroutines.launch

class FragmentMoviesListVM : ViewModel() {
    val moviesListLiveData: LiveData<List<MovieEntity>> = MovieRepositoryImpl.localMovies

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            MovieRepositoryImpl.loadMovies()
        }
    }
}