package edu.ufp.pam2022.project.listMovie

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import edu.ufp.pam2022.project.R
import edu.ufp.pam2022.project.databinding.ActivityScrollingProjectBinding
import edu.ufp.pam2022.project.listMovie.ScrollingProjectActivity

class ScrollingProjectActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingProjectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollingProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}