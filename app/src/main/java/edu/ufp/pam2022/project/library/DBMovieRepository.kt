package edu.ufp.pam2022.project.library

import androidx.lifecycle.LiveData

class DBMovieRepository(private val MovieDao: DataBaseMovieDao) {

    val readAllData: List<Movie> = MovieDao.Movie_getAll()

    suspend fun addUser(Movie: Movie){
        MovieDao.Movie_insertAll(Movie)
    }

}