package edu.atilim.ise308.inanc.project1_mymoviesapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment

class AddEditFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.add_edit_fragment, container, false)

        val editName = view.findViewById<EditText>(R.id.editText_Name)
        val editDescription = view.findViewById<EditText>(R.id.editText_Description)
        val editActor = view.findViewById<EditText>(R.id.editText_Actor)
        val editView = view.findViewById<EditText>(R.id.editText_View)
        val cbWatched = view.findViewById<CheckBox>(R.id.checkBox_isWatched)
        val buttonDone = view.findViewById<Button>(R.id.btn_done)

        //Seçilen verinin bilgilerinin ekrana yazılı gelmesi kısmı
        if (MainActivity.currentPosition != -1) {
            val currentMovie = MainActivity.movieList[MainActivity.currentPosition]
            editName.setText(currentMovie.movieName)
            editDescription.setText(currentMovie.movieDescription)
            cbWatched.isChecked = currentMovie.isWatched
            editView.setText(currentMovie.movieView.toString())
            //editView.setText(currentMovie.movieView ?: 0)
            editActor.setText(currentMovie.movieActor)
        }

        buttonDone.setOnClickListener {
            val newMovie = MovieModel()
            newMovie.movieName = editName.text.toString()
            newMovie.movieDescription = editDescription.text.toString()
            newMovie.movieActor = editActor.text.toString()
            newMovie.movieView = Integer.parseInt(editView.text.toString())
            //newMovie.movieView = editView.text.toString().toIntOrNull() ?: 0
            newMovie.isWatched = cbWatched.isChecked
            val callingActivity = activity as MainActivity
            callingActivity.createNewMovie(newMovie)
            callingActivity.hideFragment()
        }
        return view
    }
}