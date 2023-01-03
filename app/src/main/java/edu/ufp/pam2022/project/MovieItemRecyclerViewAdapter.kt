package edu.ufp.pam2022.project

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import edu.ufp.pam2022.project.databinding.FragmentMovieBinding
import edu.ufp.pam2022.project.library.Movie

/**
 * [RecyclerView.Adapter] that can display a [Movie].
 * TODO: Replace the implementation with code for your data type.
 */
class MovieItemRecyclerViewAdapter(private val values: List<Movie>) : RecyclerView.Adapter<MovieItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        
        return ViewHolder(
            FragmentMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = values[position]
        holder.idView.text = item.MovieId.toString()
        holder.contentView.text = item.Name
        holder.date.text= item.ReleaseDate.toString()

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        val idView: TextView = binding.MovieId
        val contentView: TextView = binding.MovieName
        val date: TextView =binding.MovieDateRelease

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}