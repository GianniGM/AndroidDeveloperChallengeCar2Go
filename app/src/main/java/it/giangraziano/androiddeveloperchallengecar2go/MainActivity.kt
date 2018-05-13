package it.giangraziano.androiddeveloperchallengecar2go

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import it.giangraziano.androiddeveloperchallengecar2go.network.NetworkData
import it.giangraziano.androiddeveloperchallengecar2go.adapters.PhotoListAdapter
import it.giangraziano.androiddeveloperchallengecar2go.extensions.onScrollToEnd
import it.giangraziano.androiddeveloperchallengecar2go.extensions.setLayoutManager
import it.giangraziano.androiddeveloperchallengecar2go.model.Photo
import it.giangraziano.androiddeveloperchallengecar2go.network.UnsplashService
import it.giangraziano.androiddeveloperchallengecar2go.network.NetworkLogic
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), MainView {
    override fun onError(error: String) {
        Log.e(MainActivity.TAG, error)
    }

    companion object {
        private const val TAG = "MAIN_ACTIVITY"
    }

    private val photoListRecyclerView: RecyclerView by lazy {
        items_list.setLayoutManager<StaggeredGridLayoutManager>(this)
        items_list.adapter = PhotoListAdapter()
        items_list
    }

    private lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val network = NetworkLogic(Retrofit.Builder()
                .baseUrl(NetworkData.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(UnsplashService::class.java)
        )

        presenter = PresenterImpl(this, network)
        showProgressBar()
        presenter.serve()
        photoListRecyclerView.onScrollToEnd {
            presenter.serve()
        }
    }

    override fun addData(list: MutableList<Photo>) {
        (photoListRecyclerView.adapter as PhotoListAdapter).addData(list)
        Log.d(MainActivity.TAG, list.toString())
    }


    override fun showProgressBar() {
        progress_bar.visibility = ProgressBar.VISIBLE
        error_text_message.visibility = TextView.GONE
        photoListRecyclerView.visibility = RecyclerView.GONE
    }

    override fun hideProgressBar(loadingSuccess: Boolean) {
        progress_bar.visibility = ProgressBar.GONE
        photoListRecyclerView.visibility = if (loadingSuccess) RecyclerView.VISIBLE else RecyclerView.GONE
        error_text_message.visibility = if (loadingSuccess) TextView.GONE else TextView.VISIBLE
    }
}
