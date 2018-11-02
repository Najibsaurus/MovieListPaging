package bdd.jogja.paging.db


import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [(Movie::class)], version = 1, exportSchema = false)

abstract class MovieRoomDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null
        fun createInstance(context: Context): MovieRoomDatabase =
            INSTANCE ?: synchronized(this) { INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                MovieRoomDatabase::class.java, "movie_database")
                .build()
    }
}

