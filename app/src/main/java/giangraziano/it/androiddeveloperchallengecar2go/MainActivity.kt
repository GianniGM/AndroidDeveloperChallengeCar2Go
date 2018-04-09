package giangraziano.it.androiddeveloperchallengecar2go

import android.net.Network
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import android.widget.TextView
import giangraziano.it.androiddeveloperchallengecar2go.adapters.PhotoListAdapter
import giangraziano.it.androiddeveloperchallengecar2go.extensions.onScrollToEnd
import giangraziano.it.androiddeveloperchallengecar2go.extensions.setColumnsLayout
import giangraziano.it.androiddeveloperchallengecar2go.network.NetworkUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MAIN_ACTIVITY"
    }

    private val recyclerView: RecyclerView by lazy {
        items_list.setColumnsLayout(this)
        items_list.adapter = PhotoListAdapter()
        items_list
    }

    private val network = NetworkUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showProgressBar()
        serve()

        recyclerView.onScrollToEnd {
            serve()
        }
    }

    private fun serve() {
        network.getPhotosFromService()
                ?.subscribe({
                    (recyclerView.adapter as PhotoListAdapter).setData(it)
                    hideProgressBar(true, null)
                }, {
                    hideProgressBar(false, null)
                })
    }

    private fun showProgressBar() {
        progress_bar.visibility = ProgressBar.VISIBLE
        error_text_message.visibility = TextView.GONE
        recyclerView.visibility = RecyclerView.GONE
    }

    private fun hideProgressBar(loadingSuccess: Boolean, messageText: String?) {
        progress_bar.visibility = ProgressBar.GONE
        recyclerView.visibility = if (loadingSuccess) RecyclerView.VISIBLE else RecyclerView.GONE
        error_text_message.visibility = if (loadingSuccess) TextView.GONE else TextView.VISIBLE
    }
}
