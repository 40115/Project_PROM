package edu.ufp.pam2022.project.library

import java.sql.Date

data class Backlog(
    var BackLogId: Int,
    var UserId: Int,
    var MovieId: Int,
    var WatchedDate: Date,
    var StatusId: Status,
    var rating: Int,
                      )
