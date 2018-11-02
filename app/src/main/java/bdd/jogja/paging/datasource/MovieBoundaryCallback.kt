package bdd.jogja.paging.datasource

import android.arch.paging.PagedList
import android.util.Log
import bdd.jogja.paging.BuildConfig
import bdd.jogja.paging.db.Movie
import bdd.jogja.paging.db.MovieRoomDatabase
import bdd.jogja.paging.network.ApiClient
import bdd.jogja.paging.network.MovieResponse
import bdd.jogja.paging.network.MovieService
import bdd.jogja.paging.util.PagingRequestHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class MovieBoundaryCallback(private val db: MovieRoomDatabase) : PagedList.BoundaryCallback<Movie>() {

    private val network = ApiClient.getClient()
    private val call = network.create(MovieService::class.java)
    private val TAG = MovieBoundaryCallback::class.java.simpleName
    private var firstPage = 1
    private val executor = Executors.newSingleThreadExecutor()
    private val helper = PagingRequestHelper(executor)


    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()

        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) { helperCallback ->
            call.getMovie(BuildConfig.API_KEY, firstPage)

                .enqueue(object : Callback<MovieResponse> {

                    override fun onFailure(call: Call<MovieResponse>?, t: Throwable) {

                        Log.e(TAG, "Failed")
                        helperCallback.recordFailure(t)
                    }

                    override fun onResponse(
                        call: Call<MovieResponse>?,
                        response: Response<MovieResponse>
                    ) {

                        val data = response.body()?.getResults()
                        firstPage ++
                        executor.execute {
                            db.movieDao().insert(data ?: listOf())
                            helperCallback.recordSuccess()
                        }
                    }
                })
        }

    }

    override fun onItemAtEndLoaded(itemAtEnd: Movie) {
        super.onItemAtEndLoaded(itemAtEnd)
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) { helperCallback ->
            call.getMovie(BuildConfig.API_KEY, firstPage)
                .enqueue(object : Callback<MovieResponse> {

                    override fun onFailure(call: Call<MovieResponse>?, t: Throwable) {
                        Log.e(TAG, "Failed")

                        helperCallback.recordFailure(t)
                    }

                    override fun onResponse(
                        call: Call<MovieResponse>?,
                        response: Response<MovieResponse>) {

                        val data = response.body()?.getResults()
                        firstPage ++
                        executor.execute {
                            db.movieDao().insert(data ?: listOf())
                            helperCallback.recordSuccess()
                        }
                    }
                })
        }

    }


}