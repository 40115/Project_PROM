package edu.ufp.pam2022.project.Alt_Detailed_Activity.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import edu.ufp.pam2022.project.BacklogRecyclerViewAdapter
import edu.ufp.pam2022.project.MovieItemRecyclerViewAdapter
import edu.ufp.pam2022.project.databinding.FragmentGalleryBinding
import edu.ufp.pam2022.project.library.Movie

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this,GalleryViewModelFactory(this.requireActivity()))[GalleryViewModel::class.java]

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val reclird = binding.BacklogReclinerId

        galleryViewModel.Backlog.observe(viewLifecycleOwner, Observer {
            val BacklogList = it ?: return@Observer
            if (BacklogList.isNotEmpty()) {
                val backlogItemRecyclerViewAdapter=
                    BacklogRecyclerViewAdapter(BacklogList)
                reclird.adapter=backlogItemRecyclerViewAdapter
                reclird.layoutManager = LinearLayoutManager(this.context)
            }
            else
            {
                if (galleryViewModel.checked) {
                    val empty_Movie = Movie(-1, "Movies Empty", "", 0, "", "")
                    val empty: MutableList<Movie> = mutableListOf(empty_Movie)
                    val movieItemRecyclerViewAdapter =
                        MovieItemRecyclerViewAdapter(empty)
                    reclird.adapter = movieItemRecyclerViewAdapter
                    reclird.layoutManager = LinearLayoutManager(this.context)
                }
                else
                {
                    galleryViewModel.Get_Backlog_By_Id(galleryViewModel.user.UserId)
                    galleryViewModel.checked=true
                }
            }
        })

        val textView: TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}