package edu.atilim.ise308.inanc.project1_mymoviesapp

import android.content.Context
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONTokener
import java.io.*

class JSONSerializer(private val filename: String, private val context: Context) {

    @Throws(IOException::class, JSONException::class)
    fun save (movieList: List<MovieModel>){
        val jsonArray = JSONArray()
        for(movie in movieList){
            jsonArray.put(movie.convertTOJSON())
        }
        var writer: Writer? = null
        try {
            val outFile = context.openFileOutput(filename, Context.MODE_PRIVATE)
            writer = OutputStreamWriter(outFile)
            writer.write(jsonArray.toString())

        }finally {
            if (writer != null){
                writer.close()

            }
        }
    }

    @Throws(IOException::class, JSONException::class)
    fun load():ArrayList<MovieModel>{
        var movieList = ArrayList<MovieModel>()
        var reader : BufferedReader? =null

        try {
            val inFile = context.openFileInput(filename)
            reader = BufferedReader(InputStreamReader(inFile))
            val jsonString = StringBuilder()

            for (line in reader.readLine()){
                jsonString.append(line)
            }

            val jsonArray = JSONTokener(jsonString.toString()).nextValue() as JSONArray

            for (i in 0 until jsonArray.length()){
                movieList.add(MovieModel(jsonArray.getJSONObject(i)))
            }
        }catch (e : FileNotFoundException){
            // we will ignore this one, since it happens
            //when we start fresh. You could a log statement here.
        }finally {
            reader!!.close()
        }
        return movieList
    }

}