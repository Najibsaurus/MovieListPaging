package bdd.jogja.paging.datasource

import android.arch.paging.PageKeyedDataSource
import android.util.Log
import bdd.jogja.paging.BuildConfig
import bdd.jogja.paging.db.Movie
import bdd.jogja.paging.network.ApiClient
import bdd.jogja.paging.network.MovieResponse
import retrofit2.Callback
import bdd.jogja.paging.network.MovieService
import retrofit2.Call
import retrofit2.Response


class MovieDataSource : PageKeyedDataSource<Int, Movie>() {


    private val network = ApiClient.getClient()
    private val call = network.create(MovieService::class.java)
    private val firstPage : Int = 1
    private val TAG = MovieDataSource::class.java.simpleName


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        call.getMovie(BuildConfig.API_KEY, firstPage)
        .enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                Log.e(TAG, "Failed")
            }

            override fun onResponse(
                call: Call<MovieResponse>?,
                response: Response<MovieResponse>
            ) {
                val data = response.body()?.getResults()
                callback.onResult(data ?: listOf(), null, firstPage + 1 )

            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        call.getMovie(BuildConfig.API_KEY, params.key)
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                    Log.e(TAG, "Failed")
                }
                override fun onResponse(
                    call: Call<MovieResponse>?,
                    response: Response<MovieResponse>
                ) {
                    val data = response.body()?.getResults()
                    callback.onResult(data ?: listOf(), params.key + 1)

                }
            })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        call.getMovie(BuildConfig.API_KEY, params.key)
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                    Log.e(TAG, "Failed")
                }

                override fun onResponse(
                    call: Call<MovieResponse>?,
                    response: Response<MovieResponse>
                ) {
                    val adjacentKey = if (params.key > 1) params.key - 1 else null

                    val data = response.body()?.getResults()
                    callback.onResult(data ?: listOf(), adjacentKey)

                }
            })
    }

}