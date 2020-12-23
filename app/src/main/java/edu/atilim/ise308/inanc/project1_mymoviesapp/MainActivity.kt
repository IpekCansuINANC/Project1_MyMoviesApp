package edu.atilim.ise308.inanc.project1_mymoviesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private var movieList : ArrayList<MovieModel>? = null
    private var recyclerView: RecyclerView? = null
    private var jsonSerializer: JSONSerializer?= null
    private var adapter : MovieAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fabNewMovie = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fabNewMovie.setOnClickListener{
            //FAB BUTONUNA TIKLADIĞINDA ADD EDİT FRAGMENT LAYOUT AÇILACAK
            val newMovie = AddEditFragment()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container,AddEditFragment())
            fragmentTransaction.commit()

            //val intentToMovie = Intent(this,AddEditFragment::class.java)
            //startActivity(intentToMovie)
        }

    }

    fun createNewMovie(movie: MovieModel){
        movieList!!.add(movie)
        adapter!!.notifyDataSetChanged()
    }
    fun showMovie(movieToShow: Int)  // main activity show the data
    {
        val fragment = ShowMovieFragment()
        //movieList?.get(movieToShow)?.let { fragment.sendMovieSelected(it) }
        


    }
}