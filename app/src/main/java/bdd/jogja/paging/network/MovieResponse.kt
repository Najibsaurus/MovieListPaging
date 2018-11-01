package bdd.jogja.paging.network

import bdd.jogja.paging.db.Movie
import com.google.gson.annotations.SerializedName



class MovieResponse {

    @SerializedName("page")
    private val page: Int = 0

    @SerializedName("results")
    private val results: List<Movie>? = null

    @SerializedName("total_results")
    private val totalResults: Int = 0

    @SerializedName("total_pages")
    private val totalPages: Int = 0

    fun getResults(): List<Movie>? {
        return results
    }
}