package it.giangraziano.androiddeveloperchallengecar2go.extensions

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager


fun RecyclerView.setColumnsLayout(ctx: Context) {
    val displayMetrics = ctx.resources.displayMetrics
    val dpWidth = displayMetrics.widthPixels / displayMetrics.density
    val nColumns = (dpWidth / 180).toInt()

    layoutManager = StaggeredGridLayoutManager(nColumns, StaggeredGridLayoutManager.VERTICAL)
}

fun RecyclerView.onScrollToEnd(whenScrollCloseToEnd: (Unit) -> Unit) =
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {

                val manager = layoutManager as StaggeredGridLayoutManager
                if (manager.childCount + manager.findFirstVisibleItemPositions(null)[0]
                        >= layoutManager.itemCount - 10) {
                    whenScrollCloseToEnd(kotlin.Unit)
                }
            }
        })
