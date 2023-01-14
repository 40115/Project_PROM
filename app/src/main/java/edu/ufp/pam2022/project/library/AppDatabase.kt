package edu.ufp.pam2022.project.library

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


    @Database(entities = [Backlog::class,Movie::class,Genre::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {

        abstract fun databaseMovieDao(): DataBaseMovieDao

        abstract fun databaseBacklogDao(): DataBaseBacklogDao

        abstract fun databaseGenreDao(): DataBaseGenreDao

        companion object {
            @Volatile
            private var INSTANCE: AppDatabase? = null

            fun getDatabase(context: Context): AppDatabase{
                val tempInstance = INSTANCE
                if(tempInstance != null){
                    return tempInstance
                }
                synchronized(this){
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "database-name"
                    ).build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
    }
