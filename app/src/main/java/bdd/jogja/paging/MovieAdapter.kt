package bdd.jogja.paging

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import bdd.jogja.paging.db.Movie
import bdd.jogja.paging.ui.MovieListUI
import com.bumptech.glide.Glide
import org.jetbrains.anko.AnkoContext


class MovieAdapter(private val context: Context):PagedListAdapter<Movie, MovieAdapter.MovieViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieListUI = MovieListUI()
        return MovieViewHolder(movieListUI.createView(AnkoContext.create(context)),movieListUI)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }

    class MovieViewHolder(containerView: View, movieListUI: MovieListUI) : RecyclerView.ViewHolder(containerView) {
        val title = movieListUI.title
        val image = movieListUI.image

        fun bind(movie: Movie?) {
            title.text = movie?.title
            movie?.posterPath.let {
                Glide.with(itemView).load(BuildConfig.BASE_URL_IMG + it).into(image)
            }

        }

    }
}