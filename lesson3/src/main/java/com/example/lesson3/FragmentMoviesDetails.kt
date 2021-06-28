package com.example.lesson3

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.solver.state.State
import androidx.constraintlayout.widget.ConstraintLayout


class FragmentMoviesDetails : Fragment() {

    var listener: ClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is ClickListener) {
            listener = context
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onStart() {
        super.onStart()

        view?.findViewById<TextView>(R.id.tv_back)?.setOnClickListener {
            listener?.onMovieDeselected()
        }
    }

    override fun onDetach() {
        listener = null

        super.onDetach()
    }

    interface ClickListener {
        fun onMovieDeselected()
    }

    companion object {
        fun create() = FragmentMoviesDetails()
    }
}

