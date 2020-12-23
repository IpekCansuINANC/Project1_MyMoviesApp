package edu.atilim.ise308.inanc.project1_mymoviesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ShowMovieFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.show_movie_fragment,container,false)

        val tvName = view.findViewById<TextView>(R.id.textView_name)
        val tvActor = view.findViewById<TextView>(R.id.textView_actor)
        val tvDescription = view.findViewById<TextView>(R.id.textView_description)
        //val tvView = view.findViewById<TextView>(R.id.textView_view)
        val tvIsWatched = view.findViewById<TextView>(R.id.textView_isWatched)

        tvName.text = arguments!!.getString("movieName")
        tvActor.text = arguments!!.getString("movieActor")
        tvDescription.text = arguments!!.getString("movieDescription")
        //tvView.text = arguments!!.getString("movieView")
        if(!arguments!!.getBoolean("isWathed")) tvIsWatched.visibility = View.GONE

        return view
    }

    companion object{
        fun newInstance(movieModel: MovieModel ) : ShowMovieFragment {    //purpose of the function: create a new fragment
            val fragment = ShowMovieFragment()
            val bundle = Bundle(1)
            bundle.putString("movieName", movieModel.movieName)
            bundle.putString("movieActor", movieModel.movieActor)
            bundle.putString("movieDescription", movieModel.movieDescription)
            //bundle.putInt("movieView",movieModel.movieView) // ??
            bundle.putBoolean("isWatched", movieModel.isWatched)
            fragment.arguments = bundle
            return fragment
        }
    }


}