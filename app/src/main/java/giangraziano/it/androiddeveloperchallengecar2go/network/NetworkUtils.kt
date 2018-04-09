package giangraziano.it.androiddeveloperchallengecar2go.network

import giangraziano.it.androiddeveloperchallengecar2go.model.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/**
 * Created by ggmodica on 09/04/18.
 */

class NetworkUtils(private var service: MyService? = null) {

    companion object {
        fun createService(): MyService? {
            return Retrofit.Builder()
                    .baseUrl(NetworkData.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(MyService::class.java)

        }
    }

    fun getPhotos(page: Int): Observable<Photo> {
        if (service == null)
            this.service = createService()

        return service?.getPhotos(NetworkData.client_id, page)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
    }

}