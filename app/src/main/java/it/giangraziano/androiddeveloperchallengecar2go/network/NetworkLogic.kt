package it.giangraziano.androiddeveloperchallengecar2go.network

import it.giangraziano.androiddeveloperchallengecar2go.model.Photo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class NetworkLogic(private val retrofit: UnsplashService) {

    var selectedPage: Int = 0
        private set

    fun getPhotosFromApi(): Single<MutableList<Photo>>? {
        return retrofit.getPhotos(
                NetworkData.client_id,
                ++selectedPage
        )
    }
}
