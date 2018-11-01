package bdd.jogja.paging.db


import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [(Movie::class)], version = 1)

abstract class MovieRoomDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        private lateinit var instance: MovieRoomDatabase

        fun createInstance(context: Context): MovieRoomDatabase {
            if (instance == null) {
                val instances = Room.databaseBuilder(context, MovieRoomDatabase::class.java, "movie.db")
            }
            return  instance
        }
    }

}