package it.giangraziano.androiddeveloperchallengecar2go.extensions

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView


fun RecyclerView.setColumnsLayout(ctx: Context) {
    val displayMetrics = ctx.resources.displayMetrics
    val dpWidth = displayMetrics.widthPixels / displayMetrics.density
    val nColumns = (dpWidth / 180).toInt()

    layoutManager = GridLayoutManager(ctx, nColumns)
}

fun RecyclerView.onScrollToEnd(whenScrollCloseToEnd: (Unit) -> Unit) =
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {

                val manager = layoutManager as GridLayoutManager
                if (manager.childCount + manager.findFirstVisibleItemPosition()
                        >= layoutManager.itemCount - 40) {
                    whenScrollCloseToEnd(kotlin.Unit)
                }
            }
        })
