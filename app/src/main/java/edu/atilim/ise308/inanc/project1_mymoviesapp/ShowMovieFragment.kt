package edu.atilim.ise308.inanc.project1_mymoviesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import edu.atilim.ise308.inanc.project1_mymoviesapp.MainActivity.Companion.currentPosition
import edu.atilim.ise308.inanc.project1_mymoviesapp.MainActivity.Companion.movieList

class ShowMovieFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.show_movie_fragment,container,false)
        val currentMovie = movieList[currentPosition]
        val tvName = view.findViewById<TextView>(R.id.textView_name)
        val tvActor = view.findViewById<TextView>(R.id.textView_actor)
        val tvDescription = view.findViewById<TextView>(R.id.textView_description)
        val tvView = view.findViewById<TextView>(R.id.textView_view)
        val tvIsWatched = view.findViewById<TextView>(R.id.textView_isWatched)
        val tvNotWatched = view.findViewById<TextView>(R.id.textView_notWatched)
        val btnDelete = view.findViewById<Button>(R.id.btn_delete)
        val btnEdit = view.findViewById<Button>(R.id.btn_edit)

        tvName.text = currentMovie.movieName
        tvActor.text = currentMovie.movieActor
        tvDescription.text = currentMovie.movieDescription
        tvView.text = currentMovie.movieView.toString()


        if(currentMovie.isWatched == true){
            tvNotWatched.visibility = View.GONE
        }
        if(currentMovie.isWatched == false){
            tvIsWatched.visibility = View.GONE
        }

        /*tvName.text = arguments!!.getString("movieName")
        tvActor.text = arguments!!.getString("movieActor")
        tvDescription.text = arguments!!.getString("movieDescription")
        //tvView.text = arguments!!.getString("movieView")*/
        //if(!arguments!!.getBoolean("isWatched")) tvIsWatched.visibility = View.GONE


        btnDelete.setOnClickListener {
            movieList.removeAt(currentPosition)
            val callingActivity = activity as MainActivity
            //callingActivity.hideFragment()
        }

        btnEdit.setOnClickListener {
            val callingActivity = activity as MainActivity
            callingActivity.editWithPosition(currentPosition)
        }
        return view
    }

    companion object{
        fun newInstance(movieModel: MovieModel ) : ShowMovieFragment {    //purpose of the function: create a new fragment
            val fragment = ShowMovieFragment()
            val bundle = Bundle(1)
            bundle.putString("movieName", movieModel.movieName)
            bundle.putString("movieActor", movieModel.movieActor)
            bundle.putString("movieDescription", movieModel.movieDescription)
            bundle.putInt("movieView",Integer.parseInt(movieModel.movieView.toString()))
            //bundle.putInt("movieView",movieModel.movieView) // ??
            bundle.putBoolean("isWatched", movieModel.isWatched)
            fragment.arguments = bundle
            return fragment
        }
    }


}