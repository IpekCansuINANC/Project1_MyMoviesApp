package edu.atilim.ise308.inanc.project1_mymoviesapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(private val mainActivity: MainActivity, private val movieList: List<MovieModel>): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    inner class MovieViewHolder(movieItemView: View) : RecyclerView.ViewHolder(movieItemView), View.OnClickListener {

        internal var card_view = movieItemView.findViewById<CardView>(R.id.movie_cardView)
        internal var name = movieItemView.findViewById<TextView>(R.id.movie_name)
        internal var description = movieItemView.findViewById<TextView>(R.id.movie_description)
        internal var actor = movieItemView.findViewById<TextView>(R.id.movie_actor)
        internal var movieView = movieItemView.findViewById<TextView>(R.id.movie_view)
        internal var isWatched = movieItemView.findViewById<TextView>(R.id.cb_isWatched)

        init {
            movieItemView.isClickable = true
            movieItemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            //mainActivity.showNote(adapterPosition)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        if (movieList != null) {
            return movieList.size
        }
        return -1
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.name.text = movie.movieName
        holder.actor.text = movie.movieActor
        holder.description.text = movie.movieDescription
        holder.movieView.text = movie.movieView.toString() // tekrar bak

        when {
            movie.isWatched -> holder.isWatched.text = mainActivity.resources.getString(R.string.isWatched_text)

        }
    }

}
