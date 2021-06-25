package com.example.lesson3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.solver.state.State
import androidx.constraintlayout.widget.ConstraintLayout


class FragmentMoviesDetails : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        val cl_banner = view.findViewById<ConstraintLayout>(R.id.cl_banner)
        return view
    }


}