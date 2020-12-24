package edu.atilim.ise308.inanc.project1_mymoviesapp

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
        val editDescription= view.findViewById<EditText>(R.id.editText_Description)
        val editActor = view.findViewById<CheckBox>(R.id.editText_Actor)
        val editView = view.findViewById<CheckBox>(R.id.editText_View)
        
        val cbWatched = view.findViewById<CheckBox>(R.id.checkBox_isWatched)
        val buttonDone = view.findViewById<Button>(R.id.btn_done)

       /* buttonDone.setOnClickListener{

            val newMovie = MovieModel()
            newMovie.movieName = editName.text.toString()
            newMovie.movieDescription = editDescription.text.toString()
            newMovie.movieActor = editActor.text.toString()
            //newMovie.movieView = editView.text.toString()
            newMovie.isWatched = cbWatched.isChecked

            val callingActivity = activity as MainActivity?
            callingActivity!!.createNewMovie(newMovie)

            // dismiss()

        }*/


        return view
    }


}