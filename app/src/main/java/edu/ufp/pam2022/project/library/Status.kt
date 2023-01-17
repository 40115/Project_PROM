package edu.ufp.pam2022.project.library

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Status(
    @PrimaryKey var StatusId: Int,
    @ColumnInfo(name = "Description") var description: String
)
