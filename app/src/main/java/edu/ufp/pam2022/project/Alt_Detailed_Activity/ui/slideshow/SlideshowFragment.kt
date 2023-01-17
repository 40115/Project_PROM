package edu.ufp.pam2022.project.Alt_Detailed_Activity.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import edu.ufp.pam2022.project.MovieItemRecyclerViewAdapter
import edu.ufp.pam2022.project.databinding.FragmentSlideshowBinding
import edu.ufp.pam2022.project.library.Movie

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this, SlideshowViewModelFactory(this.requireActivity()))[SlideshowViewModel::class.java]

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val reclird = binding.MovieReclinerId

        val textView: TextView = binding.textSlideshow
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        slideshowViewModel.Movies.observe(viewLifecycleOwner, Observer {
            val movieList = it ?: return@Observer
            if (!movieList.isEmpty()) {
                val movieItemRecyclerViewAdapter=
                    MovieItemRecyclerViewAdapter(movieList)
                reclird.adapter=movieItemRecyclerViewAdapter
                reclird.layoutManager = LinearLayoutManager(this.context)
            }
            else
            {
                if (slideshowViewModel.checked) {
                    val empty_Movie = Movie(-1, "Movies Empty", "", 0, "", "")
                    val empty: MutableList<Movie> = mutableListOf(empty_Movie)
                    val movieItemRecyclerViewAdapter =
                        MovieItemRecyclerViewAdapter(empty)
                    reclird.adapter = movieItemRecyclerViewAdapter
                    reclird.layoutManager = LinearLayoutManager(this.context)
                }
                else
                {
                    slideshowViewModel.Get_Movies()
                    slideshowViewModel.checked=true
                }
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}