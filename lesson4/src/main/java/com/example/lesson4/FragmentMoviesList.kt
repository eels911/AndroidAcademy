package com.example.lesson4

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {
    private val clList: ConstraintLayout by lazy {
        requireView().findViewById<ConstraintLayout>(R.id.cl_banner).apply {
            setOnClickListener {
                seleсtedMovieDetails()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clList
    }

    private fun seleсtedMovieDetails() {
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.fv_container, FragmentMoviesDetails())
            .addToBackStack(null)
            .commit()
    }
}

