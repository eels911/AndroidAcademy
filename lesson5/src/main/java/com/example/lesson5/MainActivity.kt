package com.example.lesson5


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson5.data.MovieRepositoryImpl


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            routeToMoviesList()
        }
    }

    private fun routeToMoviesList() {
        MovieRepositoryImpl.init(this)
        supportFragmentManager.beginTransaction()
                .add(
                    R.id.main_container,
                    FragmentMoviesList()
                )
                .commit()
    }
    override fun onDestroy() {
        super.onDestroy()


        MovieRepositoryImpl.release()
    }

}




