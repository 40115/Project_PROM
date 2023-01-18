package edu.ufp.pam2022.project.library

import androidx.lifecycle.LiveData

class DBMovieRepository(private val MovieDao: DataBaseMovieDao) {

    val readAllData: LiveData<List<Movie>> = MovieDao.Movie_getAll()

    suspend fun addMovie(Movie: Movie){
        MovieDao.Movie_insertAll(Movie)
    }



}