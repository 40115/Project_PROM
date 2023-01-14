package edu.ufp.pam2022.project.Alt_Detailed_Activity.ui.slideshow

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ufp.pam2022.project.library.AppDatabase
import edu.ufp.pam2022.project.library.DBMovieRepository
import edu.ufp.pam2022.project.library.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SlideshowViewModel(application: Application) : ViewModel() {

    private val _Movies = MutableLiveData<List<Movie>>()
    var Movies:LiveData<List<Movie>> = _Movies
    private val repository: DBMovieRepository
    private val _text = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }
    val text: LiveData<String> = _text
    init {
        val Moviedao = AppDatabase.getDatabase(application).databaseMovieDao()
        repository = DBMovieRepository(Moviedao)
        _Movies.value = repository.readAllData
    }

    fun addMovie(movie:Movie){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(movie)
        }
    }









}