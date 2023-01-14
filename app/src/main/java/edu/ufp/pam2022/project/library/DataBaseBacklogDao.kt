package edu.ufp.pam2022.project.library

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataBaseBacklogDao {

    @Query("SELECT * FROM Backlog")
    fun Backlog_getAll(): List<Backlog>

    @Insert
    fun Backlog_insertAll(vararg backlogs: Backlog)

}