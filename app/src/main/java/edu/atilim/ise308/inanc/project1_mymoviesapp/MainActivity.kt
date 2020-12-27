package edu.atilim.ise308.inanc.project1_mymoviesapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private var movieList = ArrayList<MovieModel>()  //mutableListOf()
    private var jsonSerializer: JSONSerializer? = null
    private var recyclerAdapter: MovieAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fabNewMovie = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fabNewMovie.setOnClickListener {
            //FAB BUTONUNA TIKLADIĞINDA ADD EDİT FRAGMENT LAYOUT AÇILACAK
            findViewById<LinearLayout>(R.id.fragmentContainer).visibility = View.VISIBLE
            val newMovie = AddEditFragment()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainer, AddEditFragment())
            fragmentTransaction.commit()
            //val intentToMovie = Intent(this,AddEditFragment::class.java)
            //startActivity(intentToMovie)
        }
        jsonSerializer = JSONSerializer("MyMovies",applicationContext)
        try {
            movieList = jsonSerializer!!.load()
        }catch (e: Exception){
            movieList = ArrayList()
            Log.e("Error loading data..","",e)
        }

        recyclerAdapter = MovieAdapter(this,movieList)
        findViewById<RecyclerView>(R.id.recyclerView).apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }

    }

    fun createNewMovie(movie: MovieModel) {
        movieList.add(movie)
        Toast.makeText(this, "${movieList.size}", Toast.LENGTH_SHORT).show()
        recyclerAdapter!!.movieList = movieList
        recyclerAdapter!!.notifyDataSetChanged()
    }

    //TODO show movie halledilecekkk!
    fun showMovie(movieToShow: Int)  // main activity show the data
    {
        val fragment = ShowMovieFragment()
        movieList?.get(movieToShow)?.let{ShowMovieFragment().arguments}

    }
    private fun saveMovies()
    {
        try {
            jsonSerializer!!.save(this.movieList)
        }catch (e:Exception){
            Log.e("Error saving data","",e)
        }
    }

    override fun onPause() {
        super.onPause()
        saveMovies()
    }

    private fun FragmentTransaction.show() {
        TODO("Not yet implemented")
    }

    fun hideFragment() {
        findViewById<LinearLayout>(R.id.fragmentContainer).visibility = View.GONE
    }


}
