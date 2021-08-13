package com.example.lesson5.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.lesson5.data.MovieRepositoryImpl
import com.example.lesson5.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class FragmentMoviesListVM: ViewModel() {
    var moviesListLiveData: MutableLiveData<List<Movie>> = MutableLiveData()

    fun makeApiCall() {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            moviesListLiveData.postValue(MovieRepositoryImpl.loadMovies())
        }
    }
}