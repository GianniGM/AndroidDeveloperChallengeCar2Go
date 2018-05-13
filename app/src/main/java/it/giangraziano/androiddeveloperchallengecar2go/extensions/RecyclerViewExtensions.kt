package it.giangraziano.androiddeveloperchallengecar2go.extensions

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

const val ITEMS_TO_LOAD = 25

inline fun <reified  T: RecyclerView.LayoutManager> RecyclerView.setLayoutManager(ctx: Context) {
    val displayMetrics = ctx.resources.displayMetrics
    val dpWidth = displayMetrics.widthPixels / displayMetrics.density
    val nColumns = (dpWidth / 180).toInt()

    layoutManager =  when(T::class.java){
        is StaggeredGridLayoutManager -> StaggeredGridLayoutManager(
                nColumns,
                StaggeredGridLayoutManager.VERTICAL
        )

        is LinearLayoutManager -> LinearLayoutManager(ctx)

        is GridLayoutManager -> GridLayoutManager(ctx, nColumns)
        else -> throw ClassNotFoundException("You can use only StaggeredGridLayout, LinearLayout or GridLayout")
    }
}

fun RecyclerView.onScrollToEnd(whenScrollCloseToEnd: (Unit) -> Unit) =
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {

                val manager = layoutManager
                val firstVisibleItemsPos = when (manager) {
                    is StaggeredGridLayoutManager ->
                         manager.findFirstVisibleItemPositions(null).max() ?: 0
                    is LinearLayoutManager ->
                         manager.findFirstVisibleItemPosition()
                    is GridLayoutManager ->
                         manager.findFirstVisibleItemPosition()
                    else -> ITEMS_TO_LOAD
                }
                val childValues = manager.childCount + firstVisibleItemsPos
                if (childValues >= layoutManager.itemCount - ITEMS_TO_LOAD) {
                    whenScrollCloseToEnd(kotlin.Unit)
                }

            }
        })
