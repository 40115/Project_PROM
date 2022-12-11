package edu.ufp.pam2022.project.library

import android.media.Rating
import java.sql.Date

data class Movie(
    var MovieId: Int,
    var Name: String,
    var Synopsis: String,
    var ReleaseDate: Date,
    var ImbRating: Int,
    var runTime: Date,
    var cover: String,
    var ageRatingId: AgeRating,
    var imbUrl: String
                 )
