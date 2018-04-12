package it.giangraziano.androiddeveloperchallengecar2go

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import it.giangraziano.androiddeveloperchallengecar2go.R
import it.giangraziano.androiddeveloperchallengecar2go.adapters.PhotoListAdapter
import it.giangraziano.androiddeveloperchallengecar2go.extensions.onScrollToEnd
import it.giangraziano.androiddeveloperchallengecar2go.extensions.setColumnsLayout
import it.giangraziano.androiddeveloperchallengecar2go.network.UnsplashService
import it.giangraziano.androiddeveloperchallengecar2go.network.NetworkData
import it.giangraziano.androiddeveloperchallengecar2go.network.NetworkUtils
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MAIN_ACTIVITY"
    }

    private val recyclerView: RecyclerView by lazy {
        items_list.setColumnsLayout(this)
        items_list.adapter = PhotoListAdapter()
        items_list
    }

    private lateinit var network: NetworkUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        network = NetworkUtils(Retrofit.Builder()
                .baseUrl(NetworkData.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(UnsplashService::class.java)
        )

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
                    Log.d(TAG, it.toString())
                    hideProgressBar(true)
                }, {
                    Log.e(TAG, it.localizedMessage)
                })
    }

    private fun showProgressBar() {
        progress_bar.visibility = ProgressBar.VISIBLE
        error_text_message.visibility = TextView.GONE
        recyclerView.visibility = RecyclerView.GONE
    }

    private fun hideProgressBar(loadingSuccess: Boolean) {
        progress_bar.visibility = ProgressBar.GONE
        recyclerView.visibility = if (loadingSuccess) RecyclerView.VISIBLE else RecyclerView.GONE
        error_text_message.visibility = if (loadingSuccess) TextView.GONE else TextView.VISIBLE
    }
}
