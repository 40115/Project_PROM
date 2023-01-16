package edu.ufp.pam2022.project.library

class DBMovieRepository(private val MovieDao: DataBaseMovieDao) {

    val readAllData: List<Movie> = MovieDao.Movie_getAll()

    suspend fun addUser(Movie: Movie){
        MovieDao.Movie_insertAll(Movie)
    }

}