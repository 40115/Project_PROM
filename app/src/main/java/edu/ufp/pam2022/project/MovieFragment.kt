package edu.ufp.pam2022.project

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.ufp.pam2022.project.library.Movie
import java.util.zip.Inflater


/**
 * A fragment representing a list of Items.
 */
class MovieFragment(Movies: List<Movie>) : Fragment() {

    private var columnCount = Movies.size
    private var movies:List<Movie>
    init {
      movies=Movies;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                view.setOnClickListener(){
                  Toast.makeText(this.context,"Hello",Toast.LENGTH_LONG).show()
                }
                adapter = MyItemRecyclerViewAdapter(movies)
            }
        }

        return view
    }



    companion object {


        const val ARG_COLUMN_COUNT = "column-count"


        @JvmStatic
        fun newInstance(columnCount: Int,movies:List<Movie>) =
            MovieFragment(movies).apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}