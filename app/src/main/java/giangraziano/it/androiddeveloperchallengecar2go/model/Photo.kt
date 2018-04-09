package giangraziano.it.androiddeveloperchallengecar2go.model

/**
 * Created by ggmodica on 09/04/18.
 */

data class Error(
        private val errors: MutableList<String>?
)

data class Photo(
        private val id: String?,
        private val urls: Urls,
        private val created_at: String?,
        private val updated_at: String?,
        private val width: Long?,
        private val height: Long?,
        private val color: String?,
        private val likes: Int?,
        private val description: String?,
        private val user: User?
)

data class Urls(
        private val raw: String?,
        private val full: String?,
        private val regular: String?,
        private val small: String?,
        private val thumb: String?
)

data class User(
        private val id: String?,
        private val username: String?,
        private val name: String?,
        private val profile_image: ProfileImage?
)

data class ProfileImage(
        private val small: String?,
        private val medium: String?,
        private val large: String?
)

