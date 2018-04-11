package giangraziano.it.androiddeveloperchallengecar2go

import giangraziano.it.androiddeveloperchallengecar2go.model.Photo
import giangraziano.it.androiddeveloperchallengecar2go.model.Urls
import giangraziano.it.androiddeveloperchallengecar2go.model.User

/**
 * Created by ggmodica on 11/04/18.
 */

const val MOCKED_URL_IMAGE = "https://www.gettyimages.ie/gi-resources/images/Homepage/Hero/UK/CMS_Creative_164657191_Kingfisher.jpg"

fun createEmptyPhotos() = mutableListOf<Photo>()

fun createPhotos(itemsNumber: Int): MutableList<Photo> {
        val list = createEmptyPhotos()

        for( index in 0 until itemsNumber){
                list.add(createPhoto())
        }
        return list
}

fun createPhoto() = Photo(
        "12345670",
        Urls(
                MOCKED_URL_IMAGE,
                MOCKED_URL_IMAGE,
                MOCKED_URL_IMAGE,
                MOCKED_URL_IMAGE,
                MOCKED_URL_IMAGE
        ),
        "yesterday",
        "yesterday",
        100,
        100,
        "green",
        20,
        "a flying bird",
        User(
                "123",
                "John",
                "Doe",
                null
        )
)