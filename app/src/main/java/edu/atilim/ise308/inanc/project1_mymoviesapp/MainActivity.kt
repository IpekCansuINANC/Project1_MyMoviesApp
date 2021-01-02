package edu.atilim.ise308.inanc.project1_mymoviesapp

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    //GROUP-15 MYMOVIEAPP

    companion object {
        //val movieList = mutableListOf<MovieModel>()
        val movieList = ArrayList<MovieModel>()
        var currentPosition = -1
    }

    private var jsonSerializer: JSONSerializer? = null
    private var recyclerAdapter = MovieAdapter(emptyList()) {
        currentPosition = it
        showMovie(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //FloatingActionButton -> opens a add_edit_fragment
        val fabNewMovie = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fabNewMovie.setOnClickListener {
            findViewById<LinearLayout>(R.id.fragmentContainer).visibility = View.VISIBLE
            findViewById<RecyclerView>(R.id.recyclerView).visibility = View.GONE
            currentPosition = -1
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            //Animation
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
            fragmentTransaction.replace(R.id.fragmentContainer, AddEditFragment())
            fragmentTransaction.commit()
        }
    }


    fun createNewMovie(movie: MovieModel) { //Creating new Movie
        if (currentPosition != -1) {
            movieList.removeAt(currentPosition)
            movieList.add(currentPosition, movie)
        } else {
            movieList.add(movie)
        }
        recyclerAdapter.notifyDataSetChanged()
    }

    fun showMovie(movieToShow: Int)  //Main activity show the data
    {
        findViewById<LinearLayout>(R.id.fragmentContainer).visibility = View.VISIBLE
        findViewById<RecyclerView>(R.id.recyclerView).visibility = View.GONE
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
        fragmentTransaction.replace(R.id.fragmentContainer, ShowMovieFragment())
        fragmentTransaction.commit()
    }

    private fun FragmentTransaction.show() {
        TODO("Not yet implemented")
    }

    fun hideFragment() {
        findViewById<LinearLayout>(R.id.fragmentContainer).visibility = View.GONE
        findViewById<RecyclerView>(R.id.recyclerView).visibility = View.VISIBLE
        clearFragment()
        recyclerAdapter.notifyDataSetChanged()
    }

    private fun clearFragment() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        fragment?.let {
            supportFragmentManager.beginTransaction().remove(fragment).commit()
        }
    }

    //Back Pressed -> when click back button, bring the old fragment page
    override fun onBackPressed() {
        if (findViewById<LinearLayout>(R.id.fragmentContainer).visibility == View.VISIBLE) {
            findViewById<LinearLayout>(R.id.fragmentContainer).visibility = View.GONE
            findViewById<RecyclerView>(R.id.recyclerView).visibility = View.VISIBLE
        } else {
            super.onBackPressed()
        }
    }

    fun editWithPosition(currentPosition: Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction() //Brings the fragment which belongs to current item for editing
        fragmentTransaction.replace(R.id.fragmentContainer, AddEditFragment())
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
        fragmentTransaction.commit()
    }

    override fun onPause() {
        super.onPause()
        JSONSerializer("MyMovies", this).save(movieList) //backup.txt
    }

    override fun onStart() {
        super.onStart()
        //val data = JSONSerializer("backup.txt", this).load() //changesss
        val data = JSONSerializer("MyMovies", this).load()
        movieList.addAll(data)
        Log.e("tag","${movieList.size}") //deneme
        findViewById<RecyclerView>(R.id.recyclerView).apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
        recyclerAdapter.movieList = movieList
        recyclerAdapter.notifyDataSetChanged()
    }
}
