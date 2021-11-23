package com.example.lesson8.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson8.R
import com.example.lesson8.view.FragmentMoviesList


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, FragmentMoviesList())
                .commit()
        }
    }
}