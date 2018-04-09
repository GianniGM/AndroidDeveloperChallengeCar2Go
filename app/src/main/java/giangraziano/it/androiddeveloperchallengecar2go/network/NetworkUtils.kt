package giangraziano.it.androiddeveloperchallengecar2go.network

import giangraziano.it.androiddeveloperchallengecar2go.model.Photo
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/**
 * Created by ggmodica on 09/04/18.
 */

class NetworkUtils {

    private val retrofit = Retrofit.Builder()
            .baseUrl(NetworkData.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MyService::class.java)

    private var selectedPage: Int = 1
    fun getPhotosFromService(): Single<MutableList<Photo>>? {
        return retrofit
                .getPhotos(
                        NetworkData.client_id,
                        selectedPage++
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
