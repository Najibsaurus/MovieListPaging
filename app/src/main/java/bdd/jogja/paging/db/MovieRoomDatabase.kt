package bdd.jogja.paging.db


import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [(Movie::class)], version = 1, exportSchema = false)

abstract class MovieRoomDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Synchronized
        fun createInstance(context: Context): MovieRoomDatabase {
            val instance = Room.databaseBuilder(context, MovieRoomDatabase::class.java, "movie_database")
            return instance.build()
        }
    }
}

