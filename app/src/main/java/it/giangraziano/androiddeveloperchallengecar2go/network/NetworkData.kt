package it.giangraziano.androiddeveloperchallengecar2go.network

import it.giangraziano.androiddeveloperchallengecar2go.BuildConfig


class NetworkData {
    /* PLEASE: PUT YOUR CLIENT ID INTO secret.properties file
     * client_id=3243234234242334242424342 */
    companion object {
        const val client_id = BuildConfig.client_id
        const val baseUrl="https://api.unsplash.com"
    }
}