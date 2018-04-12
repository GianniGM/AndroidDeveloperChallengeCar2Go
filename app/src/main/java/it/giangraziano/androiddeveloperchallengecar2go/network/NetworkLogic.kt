package it.giangraziano.androiddeveloperchallengecar2go.network

import it.giangraziano.androiddeveloperchallengecar2go.model.Photo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class NetworkLogic(private val retrofit: UnsplashService) {

    private var selectedPage: Int = 1

    fun getPhotosFromApi(): Single<MutableList<Photo>>? {
        return retrofit
                .getPhotos(
                        NetworkData.client_id,
                        selectedPage++
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
