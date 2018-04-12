package it.giangraziano.androiddeveloperchallengecar2go.network

import it.giangraziano.androiddeveloperchallengecar2go.model.Photo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashService {
    @GET("/photos/")
    fun getPhotos(
            @Query("client_id") clientId: String,
            @Query("page") page: Int
    ): Single<MutableList<Photo>>

}