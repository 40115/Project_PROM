package edu.ufp.pam2022.project.listMovie


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.ufp.pam2022.project.MyItemRecyclerViewAdapter
import edu.ufp.pam2022.project.MyItemRecyclerViewAdapter2
import edu.ufp.pam2022.project.R
import edu.ufp.pam2022.project.databinding.ActivityMainMovieFragmentsBinding
import edu.ufp.pam2022.project.library.User
import edu.ufp.pam2022.project.main.login.ui.login.LoginMainActivity


class MainMovieFragments : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {
    private lateinit var binding:ActivityMainMovieFragmentsBinding
    private lateinit var scrollingProjectActivityViewModel : ScrollingProjectActivityViewModel
    private lateinit var user : User
    private var movie= true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMovieFragmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        user=User(intent.getIntExtra("UserId",0),
            intent.getStringExtra("Username").toString(),
            intent.getStringExtra("Username").toString())
        if(user.UserId==0){
            val intent = Intent(this@MainMovieFragments, LoginMainActivity::class.java)
            intent.putExtra("EXIT", false)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

         scrollingProjectActivityViewModel = ViewModelProvider(this, ScrollingProjectActivityViewModelFactory(this))[ScrollingProjectActivityViewModel::class.java]
         scrollingProjectActivityViewModel.Get_Movies()
        val reclird = findViewById<View>(R.id.ReclienerMovies) as RecyclerView
        val id=binding.navigationView

         scrollingProjectActivityViewModel.Movies.observe(this@MainMovieFragments, Observer
         {
            val loginState = it ?: return@Observer

             if (loginState.isNotEmpty()){
                     val myItemRecyclerViewAdapter=
                         MyItemRecyclerViewAdapter(loginState)
                     reclird.adapter=myItemRecyclerViewAdapter
                     reclird.layoutManager=LinearLayoutManager(this)

                 }
         })
        scrollingProjectActivityViewModel.Backlog.observe(this@MainMovieFragments, Observer
        {
            val loginState = it ?: return@Observer
            if (loginState.isNotEmpty() ) {
                val myItemRecyclerViewAdapter2=
                    MyItemRecyclerViewAdapter2(loginState)
                reclird.adapter=myItemRecyclerViewAdapter2
                reclird.layoutManager=LinearLayoutManager(this)
            }


        })
    }

    fun showPopup(view: View) {
        PopupMenu(this, view).apply {
            // MainActivity implements OnMenuItemClickListener
            setOnMenuItemClickListener(this@MainMovieFragments)
            inflate(R.menu.navbar)
            show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navbar, menu)
        return true
    }
    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.title) {
            "My Collections"-> {
                if (movie) {
                    scrollingProjectActivityViewModel.Get_Backlog_By_Id(user.UserId)
                }
                else
                {
                    Toast.makeText(this,"You already are in Movies",Toast.LENGTH_SHORT)
                }
                true
            }
            "Movie"-> {
                if (!movie){
                    scrollingProjectActivityViewModel.Get_Movies()
                }
                else{
                    Toast.makeText(this,"You already are in Collections",Toast.LENGTH_SHORT)
                }
                true
            }
            "Logout"-> {
                Toast.makeText(this,"Bye"+user.Username, Toast.LENGTH_SHORT).show()
                val switchActivityIntent = Intent(this, LoginMainActivity::class.java)
                startActivity(switchActivityIntent)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }



}