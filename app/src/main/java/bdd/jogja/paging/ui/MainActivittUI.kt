package bdd.jogja.paging.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.wrapContent



class MainActivityUI : AnkoComponent<Context> {

    lateinit var movieRecyclerView: RecyclerView


    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        constraintLayout {
            movieRecyclerView = recyclerView().lparams(width = matchParent, height = wrapContent)
        }
    }
}