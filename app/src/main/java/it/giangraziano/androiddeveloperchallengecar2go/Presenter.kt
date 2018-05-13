package it.giangraziano.androiddeveloperchallengecar2go

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import it.giangraziano.androiddeveloperchallengecar2go.network.NetworkLogic

interface Presenter {
    fun serve()
    fun dispose()
}

class PresenterImpl(
        private val view: MainView,
        private val network: NetworkLogic
) : Presenter {

    private var disposable: Disposable? = null

    override fun serve() {
        disposable = network.getPhotosFromApi()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    view.hideProgressBar(true)
                }, {
                    view.onError(it.localizedMessage)
                })
    }

    override fun dispose() {
        val disposed = disposable?.isDisposed ?: false
        if (!disposed) disposable?.dispose()
    }
}