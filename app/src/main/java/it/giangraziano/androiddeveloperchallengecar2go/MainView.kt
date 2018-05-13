package it.giangraziano.androiddeveloperchallengecar2go

import it.giangraziano.androiddeveloperchallengecar2go.model.Photo

interface MainView {
    fun addData(list: MutableList<Photo>)
    fun showProgressBar()
    fun onError(error: String)
    fun hideProgressBar(loadingSuccess: Boolean)
}