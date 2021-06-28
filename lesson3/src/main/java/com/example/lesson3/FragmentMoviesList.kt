package com.example.lesson3

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout

class FragmentMoviesList : Fragment() {
 var clickListener: ClickListener? = null
    private var clList: ConstraintLayout? = null

    //среди контекста activity найти listener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickListener)
            clickListener = context
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clList = view.findViewById<ConstraintLayout>(R.id.cl_banner).apply {
            setOnClickListener{clickListener?.onSeleсtedMovieDetails()}
        }
    }

    override fun onDetach() {
        clickListener=null
        super.onDetach()
    }

interface ClickListener{
    fun onSeleсtedMovieDetails()
}
    companion object {
        fun create() = FragmentMoviesList()
    }
}