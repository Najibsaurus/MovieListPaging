package bdd.jogja.paging

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import bdd.jogja.paging.db.Movie
import bdd.jogja.paging.ui.MainActivityUI
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this).get(MovieViewModel::class.java)
    }
    private val adapter = MovieAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }
    private fun init(){
        val mainActivityUI = MainActivityUI()
        mainActivityUI.setContentView(this)
        mainActivityUI.movieRecyclerView.layoutManager = LinearLayoutManager(this)
        mainActivityUI.movieRecyclerView.adapter = adapter
        viewModel.getAllMovies().observe(this, Observer<PagedList<Movie>> { pagedList->
            adapter.submitList(pagedList)
        })


    }
}
