package bdd.jogja.paging.ui

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import bdd.jogja.paging.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView


class MovieListUI : AnkoComponent<Context> {

    lateinit var image: ImageView
    lateinit var title: TextView

    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        cardView {
            lparams(width = matchParent, height = wrapContent){
                cardElevation = dip(1).toFloat()
                radius = dip(3).toFloat()
                useCompatPadding = true
            }
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.HORIZONTAL
                padding = dip(10)

                image = imageView(android.R.drawable.btn_default).lparams(width = dip(75), height = dip(100)){

                }

                title = textView(R.string.title) {
                    textColor = Color.BLACK
                    typeface = Typeface.DEFAULT
                    textSize = sp(7).toFloat()
                }.lparams(width = wrapContent, height = wrapContent) {
                    gravity = Gravity.CENTER_VERTICAL
                    padding = dip(5)
                    margin = dip(2)

                }
            }
        }

    }

}
