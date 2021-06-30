package com.example.lesson3

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment


class FragmentMoviesDetails : Fragment(R.layout.fragment_movies_details) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.tv_back)?.setOnClickListener {
            // TODO: 6/30/21 сделпть recycleview

        }
    }


}
