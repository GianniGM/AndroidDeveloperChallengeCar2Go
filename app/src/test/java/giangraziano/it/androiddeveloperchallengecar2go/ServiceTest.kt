package giangraziano.it.androiddeveloperchallengecar2go

import giangraziano.it.androiddeveloperchallengecar2go.model.Photo
import giangraziano.it.androiddeveloperchallengecar2go.network.MyService
import giangraziano.it.androiddeveloperchallengecar2go.network.NetworkUtils
import io.reactivex.Single.just
import io.reactivex.subscribers.TestSubscriber
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

/**
 * Created by ggmodica on 11/04/18.
 */
class ServiceTest {

    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private var service: MyService? = null

    @InjectMocks
    private var network: NetworkUtils? = null

    @Test
    fun emptyTest() {
        service
                ?.getPhotos("client_id", 0)
    }

    @Test
    fun asyncTest() {
        `when`(service
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

    @Test
    fun usingTestSubscriber() {
        val photos = createPhotos()
        val subscriber = TestSubscriber<>()

        `when`(service
                ?.getPhotos("client_id", 0))
                .thenReturn(
                        just(photos)
                )

    }
}