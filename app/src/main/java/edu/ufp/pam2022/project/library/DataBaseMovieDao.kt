package edu.ufp.pam2022.project.library

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataBaseMovieDao {


        @Query("SELECT * FROM Movie")
        fun Movie_getAll(): List<Movie>

        @Insert
        fun Movie_insertAll(vararg movies: Movie)




}