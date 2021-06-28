package com.example.lesson3


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.TextView


class MainActivity : AppCompatActivity(), FragmentMoviesDetails.ClickListener,FragmentMoviesList.ClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            routeToMoviesList()
        }
    }

   override fun onSeleсtedMovieDetails(){
       seleсtedMovieDetails()
   }
    override fun onMovieDeselected() {
        routeBack()
    }
    private fun routeToMoviesList() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fv_container, FragmentMoviesList.create())
                .addToBackStack(null)
                .commit()
    }
    private fun seleсtedMovieDetails() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fv_container, FragmentMoviesDetails.create())
                .addToBackStack(null)
                .commit()
    }

    private fun routeBack() {
        supportFragmentManager.popBackStack()
    }
}




