package it.giangraziano.androiddeveloperchallengecar2go

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import it.giangraziano.androiddeveloperchallengecar2go.network.NetworkData
import it.giangraziano.androiddeveloperchallengecar2go.adapters.PhotoListAdapter
import it.giangraziano.androiddeveloperchallengecar2go.extensions.onScrollToEnd
import it.giangraziano.androiddeveloperchallengecar2go.extensions.setColumnsLayout
import it.giangraziano.androiddeveloperchallengecar2go.network.UnsplashService
import it.giangraziano.androiddeveloperchallengecar2go.network.NetworkLogic
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MAIN_ACTIVITY"
    }

    private val photoListRecyclerView: RecyclerView by lazy {
        items_list.setColumnsLayout(this)
        items_list.adapter = PhotoListAdapter()
        items_list
    }

    private lateinit var network: NetworkLogic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        network = NetworkLogic(Retrofit.Builder()
                .baseUrl(NetworkData.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(UnsplashService::class.java)
        )

        showProgressBar()
        serve()
        photoListRecyclerView.onScrollToEnd {
            serve()
        }
    }

    private fun serve() {
        network.getPhotosFromApi()
                ?.subscribe({
                    (photoListRecyclerView.adapter as PhotoListAdapter).addData(it)
                    Log.d(TAG, it.toString())
                    hideProgressBar(true)
                }, {
                    Log.e(TAG, it.localizedMessage)
                })
    }

    private fun showProgressBar() {
        progress_bar.visibility = ProgressBar.VISIBLE
        error_text_message.visibility = TextView.GONE
        photoListRecyclerView.visibility = RecyclerView.GONE
    }

    private fun hideProgressBar(loadingSuccess: Boolean) {
        progress_bar.visibility = ProgressBar.GONE
        photoListRecyclerView.visibility = if (loadingSuccess) RecyclerView.VISIBLE else RecyclerView.GONE
        error_text_message.visibility = if (loadingSuccess) TextView.GONE else TextView.VISIBLE
    }
}
