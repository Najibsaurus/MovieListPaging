package bdd.jogja.paging.network
import bdd.jogja.paging.BuildConfig
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


class ApiClient {

    companion object {
        private const val baseUrl = BuildConfig.BASE_URL
        private lateinit var retrofit: Retrofit

        fun getClient(): Retrofit {
            retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit
        }
    }


}