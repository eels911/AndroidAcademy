package com.example.lesson5.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson5.data.MovieRepositoryImpl
import com.example.lesson5.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class FragmentMovieDetailsVM: ViewModel() {
    var movieListLiveData: MutableLiveData<Movie> = MutableLiveData()

    fun loadDetails(id: Int){
        CoroutineScope(viewModelScope.coroutineContext).launch {
            //postValue() перенаправит вызов в UI поток
            movieListLiveData.postValue(MovieRepositoryImpl.loadMovie(id))
        }
    }
}