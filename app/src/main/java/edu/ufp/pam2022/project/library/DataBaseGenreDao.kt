package edu.ufp.pam2022.project.library

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface DataBaseGenreDao {

    @Query("SELECT * FROM Genre")
    fun Genre_getAll(): List<Genre>

    @Insert
    fun Genre_insertAll(vararg genres: Genre)
}