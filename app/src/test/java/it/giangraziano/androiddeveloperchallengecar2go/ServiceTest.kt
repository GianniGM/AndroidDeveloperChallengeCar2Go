package it.giangraziano.androiddeveloperchallengecar2go

import it.giangraziano.androiddeveloperchallengecar2go.network.UnsplashService
import it.giangraziano.androiddeveloperchallengecar2go.network.NetworkUtils
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.Single.just
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import it.giangraziano.androiddeveloperchallengecar2go.createPhotos
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when` as whenever
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit


class ServiceTest {

    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private var service: UnsplashService? = null

    @InjectMocks
    private var network: NetworkUtils? = null

    @Test
    fun emptyTest() {
        service
                ?.getPhotos("client_id", 0)
    }

    @Test
    fun asyncTest() {
        whenever(service
                ?.getPhotos("client_id", 0))
                .thenReturn(
                        just(createPhotos(23))
                )
    }

    @Test
    fun singleSubscribing() {
        val l = service?.getPhotos("client_id", 0)
                ?.blockingGet()
        assert(l?.size == 4)
    }

    //    @Test
//    fun usingTestSubscriber() {
//        val photos = createPhotos(3)
//        val subscriber = TestSubscriber<>()
//
//        whenever(service
//                ?.getPhotos("client_id", 0))
//                .thenReturn(
//                        just(photos)
//                )
//
//    }
    private val immediate = object : Scheduler() {
        override fun scheduleDirect(run: Runnable,
                                    delay: Long, unit: TimeUnit): Disposable {
            return super.scheduleDirect(run, 0, unit)
        }

        override fun createWorker(): Scheduler.Worker {
            return ExecutorScheduler.ExecutorWorker(
                    Executor { it.run() })
        }
    }

    @Test
    fun testLoadingServiceThen() {
        // Given
        val data = createPhotos(10)

        // When
        whenever(service?.getPhotos("client_id", 0))
                .thenReturn(Single.just(data))

        //Assert
        assertNotNull("Not Null", network?.getPhotosFromService())
        assert(network?.getPhotosFromService()?.test()?.isTerminated ?: false)
        network?.getPhotosFromService()?.test()
                ?.assertSubscribed()
                ?.assertValues(data)
                ?.assertComplete()
                ?.assertNoErrors()
    }
}