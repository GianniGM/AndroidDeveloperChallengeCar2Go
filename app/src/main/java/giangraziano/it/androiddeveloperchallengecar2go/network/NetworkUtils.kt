package giangraziano.it.androiddeveloperchallengecar2go.network

import giangraziano.it.androiddeveloperchallengecar2go.model.Photo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by ggmodica on 09/04/18.
 */

class NetworkUtils(private val retrofit: UnsplashService) {

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
