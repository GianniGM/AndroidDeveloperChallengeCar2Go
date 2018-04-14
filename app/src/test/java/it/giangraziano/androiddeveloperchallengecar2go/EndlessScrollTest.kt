package it.giangraziano.androiddeveloperchallengecar2go

import it.giangraziano.androiddeveloperchallengecar2go.network.NetworkLogic
import it.giangraziano.androiddeveloperchallengecar2go.network.UnsplashService
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class EndlessScrollTest {

    @get:Rule
    public var rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private var service: UnsplashService? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @InjectMocks
    private var network: NetworkLogic? = null

    @Test
    fun onServeOneTime_requestSuccess_pageIncremented() {

        val initialPage = network?.selectedPage ?: 0

        //mock my rxJava
        val testObserver = network?.getPhotosFromApi()?.test()
        testObserver?.awaitTerminalEvent()
        testObserver?.assertNoErrors()

        assert(network?.selectedPage == initialPage + 1)
    }

    @Test
    fun onServeMoreThanOneTime_requestSuccess_Incremented() {

        val numberOfCalls = 5
        val initialPage = network?.selectedPage ?: 0

        for(i in 0 until numberOfCalls) {
            //mock my rxJava
            val testObserver = network?.getPhotosFromApi()?.test()
            testObserver?.awaitTerminalEvent()
            testObserver?.assertNoErrors()
        }

        assert(network?.selectedPage == initialPage + numberOfCalls)
    }
}
