package bdd.jogja.paging.network

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query


interface MovieService {
    @GET("discover/movie?")
    fun getMovie(@Query("api_key") key: String, @Query("page") page: Int): Call<MovieResponse>
}