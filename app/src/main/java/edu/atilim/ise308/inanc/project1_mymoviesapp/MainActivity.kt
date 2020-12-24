package edu.atilim.ise308.inanc.project1_mymoviesapp

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private var movieList = mutableListOf<MovieModel>()
    private var jsonSerializer: JSONSerializer? = null
    private var recyclerAdapter: MovieAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerAdapter = MovieAdapter(movieList)
        findViewById<RecyclerView>(R.id.recyclerView).apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
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

    }

    fun createNewMovie(movie: MovieModel) {
        movieList.add(movie)
        Toast.makeText(this, "${movieList.size}", Toast.LENGTH_SHORT).show()
        recyclerAdapter!!.movieList = movieList
        recyclerAdapter!!.notifyDataSetChanged()
    }

    fun showMovie(movieToShow: Int)  // main activity show the data
    {
        val fragment = ShowMovieFragment()
        //movieList?.get(movieToShow)?.let { fragment.sendMovieSelected(it) }


    }

    private fun FragmentTransaction.show() {
        TODO("Not yet implemented")
    }

    fun hideFragment() {
        findViewById<LinearLayout>(R.id.fragmentContainer).visibility = View.GONE
    }
}
