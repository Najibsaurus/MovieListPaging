package bdd.jogja.paging.db

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query


@Dao
interface MovieDao {
    companion object {
        const val tableName = "movies"
    }
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: List<Movie>)

    @Query("SELECT * FROM $tableName")
    fun getAllMovies(): DataSource.Factory<Int, Movie>

    @Query("DELETE FROM $tableName")
    fun deleteAll()

}