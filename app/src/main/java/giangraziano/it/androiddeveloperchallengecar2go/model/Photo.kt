package giangraziano.it.androiddeveloperchallengecar2go.model

/**
 * Created by ggmodica on 09/04/18.
 */

data class Error(
        private val errors: MutableList<String>?
)

data class Photo(
        val id: String?,
        val urls: Urls,
        val created_at: String?,
        val updated_at: String?,
        val width: Long?,
        val height: Long?,
        val color: String?,
        val likes: Int?,
        val description: String?,
        val user: User?
)

data class Urls(
        val raw: String?,
        val full: String?,
        val regular: String?,
        val small: String?,
        val thumb: String?
)

data class User(
        val id: String?,
        val username: String?,
        val name: String?,
        val profile_image: ProfileImage?
)

data class ProfileImage(
        val small: String?,
        val medium: String?,
        val large: String?
)

