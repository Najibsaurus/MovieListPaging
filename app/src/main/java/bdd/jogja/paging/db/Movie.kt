package bdd.jogja.paging.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = MovieDao.tableName)
data class Movie(
    @PrimaryKey(autoGenerate = true) val id:Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date") val releaseDate: String
)
