package giangraziano.it.androiddeveloperchallengecar2go.network

import giangraziano.it.androiddeveloperchallengecar2go.model.Photo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ggmodica on 09/04/18.
 */

interface MyService {
    @GET("photos")
    fun getPhotos(@Query("client_id") clientId: String, @Query("page") page: Int): Single<Photo>
}