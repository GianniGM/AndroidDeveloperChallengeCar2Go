package giangraziano.it.androiddeveloperchallengecar2go

import io.reactivex.Observable
import io.reactivex.Single
import org.mockito.configuration.DefaultMockitoConfiguration
import org.mockito.internal.stubbing.defaultanswers.ReturnsEmptyValues
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer

/**
 * In this class we define when a method returns an Observable (or a Single), the value returned is an Observable (or a Single) that emits an exception.
 * In this way executing the previous test we get an exception that says that we need to define the behaviour of a mock (and not a NullPointerException!).
 * */
class MockitoConfiguration : DefaultMockitoConfiguration() {

    override fun getDefaultAnswer(): Answer<Any?> {
        return object : ReturnsEmptyValues() {
            override fun answer(invocation: InvocationOnMock?): Any? {

                if (invocation == null) {
                    return super.answer(invocation)
                }

                val type = invocation.method.returnType as Class<*>

                if (type.isAssignableFrom(Observable::class.java)) {
                    return Observable.error<Any>(createException(invocation))

                } else if (type.isAssignableFrom(Single::class.java)) {
                    return Single.error<Any>(createException(invocation))

                }
                return super.answer(invocation)
            }
        }
    }

    fun createException(invocation: InvocationOnMock?): RuntimeException {
        val exception = invocation.toString()
        return RuntimeException("No mock defined for invocation $exception")
    }
}