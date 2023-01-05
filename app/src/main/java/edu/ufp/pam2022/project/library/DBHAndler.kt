package edu.ufp.pam2022.project.library

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class DBHAndler(context: Context) : SQLiteOpenHelper(context, "Project.db", null, 1) {
    var MovieTable = "Movies"


    var UserTable = "Users"
    var User_Id="id"
    var User_username="username"


    var StatusTable = "Statuses"
    var AgeRatingTable = "AgeRatings"
    val GenresTable="Genres"
    val BacklogTable = "Backlogs"



    override fun onCreate(db: SQLiteDatabase) {
        val CreateTableUsers= "CREATE TABLE $UserTable " +
                "(id INT NOT NULL AUTO_INCREMENT,email VARCHAR(100) NOT NULL," +
                "username VARCHAR(20) NOT NULL,password VARCHAR(64) NOT NULL," +
                "PRIMARY KEY(id),UNIQUE (email),UNIQUE (username))"

        val CreateTableStatus="CREATE TABLE $AgeRatingTable (" +
                "id INT NOT NULL AUTO_INCREMENT," +
                "    description VARCHAR(50) NOT NULL," +
                "    PRIMARY KEY(id)" +
                ")"

        val CreateTableAgeRaing ="CREATE TABLE $StatusTable (" +
                "id INT NOT NULL AUTO_INCREMENT," +
                " description VARCHAR(50) NOT NULL," +
                " PRIMARY KEY(id)" +
                ")"

        val CreateTableGenre="CREATE TABLE $GenresTable (" +
                "id INT NOT NULL , AUTO_INCREMENT," +
                "    description VARCHAR(50) NOT NULL," +
                "    PRIMARY KEY(id)" +
                ")"

        val CreateTableMovies="CREATE TABLE $MovieTable (" +
                "   id INT NOT NULL AUTO_INCREMENT," +
                "    name VARCHAR(100) NOT NULL," +
                "    synopsis TEXT," +
                "    releaseDate DATE NOT NULL," +
                "    imdbRating INT," +
                "    runTime TIME," +
                "    cover BLOB," +
                "    ageRatingId INT," +
                "    imdbUrl VARCHAR(255)," +
                "    PRIMARY KEY (id)," +
                "    FOREIGN KEY (ageRatingId) REFERENCES AgeRatings(id)\n" +
                ");"

        val CreateTableBacklogs="CREATE TABLE $BacklogTable (" +
                "    id INT NOT NULL , AUTO_INCREMENT , " +
                "    userId INT NOT NULL," +
                "    movieId INT NOT NULL," +
                "    watchedDate DATETIME," +
                "    statusId INT NOT NULL," +
                "    rating INT," +
                "    PRIMARY KEY (id)," +
                "    FOREIGN KEY (userId) REFERENCES Users(id)," +
                "    FOREIGN KEY (movieId) REFERENCES Movies(id)," +
                "    FOREIGN KEY (statusId) REFERENCES Statuses(id)" +
                ");"
        db.execSQL(CreateTableUsers)
        db.execSQL(CreateTableAgeRaing)
        db.execSQL(CreateTableStatus)
        db.execSQL(CreateTableGenre)
        db.execSQL(CreateTableMovies)
        db.execSQL(CreateTableBacklogs)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


}
