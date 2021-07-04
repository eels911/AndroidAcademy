package com.example.lesson4

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class FragmentMoviesDetails : Fragment(R.layout.fragment_movies_details) {
    var listener: BackClickListener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is BackClickListener) {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.tv_back)?.setOnClickListener {
            listener?.onMovieDeselected()

        }
    }
    interface BackClickListener {
        fun onMovieDeselected()
    }

}
