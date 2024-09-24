package com.kliachenko.data.core

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

class HandleErrorTest {

    private lateinit var provideResources: FakeProvideResources
    private lateinit var handleError: HandleError<String>

    @Before
    fun setup() {
        provideResources = FakeProvideResources()
        handleError = HandleError.Base(provideResources)
    }

    @Test
    fun testUnknownHostException() {
        val expected = "No internet connection"
        val actual = handleError.handle(UnknownHostException())
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testServiceUnavailable() {
        val expected = "Service unavailable"
        val actual = handleError.handle(IllegalStateException())
        Assert.assertEquals(expected, actual)
    }
}

private class FakeProvideResources : ProvideResources {
    override fun noInternetConnectionMessage() = "No internet connection"

    override fun serviceUnavailableMessage() = "Service unavailable"

}
