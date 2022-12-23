package edu.ufp.pam2022.project.library

import java.time.LocalDate

data class Movie(
    var MovieId: Int,
    var Name: String,
    var ReleaseDate: LocalDate,
    var ImbRating: Int,
    var runTime: String,
    var ageRatingId: String,
                 )
