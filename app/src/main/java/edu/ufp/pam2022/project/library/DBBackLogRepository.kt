package edu.ufp.pam2022.project.library

import androidx.lifecycle.LiveData

class DBBackLogRepository(private val BackLogDao: DataBaseBacklogDao) {

    val readAllData: LiveData<List<Backlog>> = BackLogDao.Backlog_getAll()

    suspend fun addBacklog(backlog: Backlog){
        BackLogDao.Backlog_insertAll(backlog)
    }

    suspend fun CLear_Backlog(){
        BackLogDao.Backlog_delete()
    }
}