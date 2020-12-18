package edu.atilim.ise308.inanc.project1_mymoviesapp

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private var movieList : ArrayList<MovieModel>? = null
    private var recyclerView: RecyclerView? = null
    private var jsonSerializer: JSONSerializer?= null
    private var adapter : MovieAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun showMovie(movieToShow: Int)  // main activity show the data
    {


    }
}