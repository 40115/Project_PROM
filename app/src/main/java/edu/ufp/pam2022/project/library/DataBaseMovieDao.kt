package edu.ufp.pam2022.project.library

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataBaseMovieDao {


        @Query("SELECT * FROM Movie")
        fun Movie_getAll(): LiveData<List<Movie>>

        @Insert
        fun Movie_insertAll(vararg movies: Movie)

        @Query("DELETE FROM Movie")
        fun Movie_delete()

}