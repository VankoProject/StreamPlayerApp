package com.kliachenko.data.core

import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

interface HandleError<T : Any> {

    fun handle(e: Exception): T

    @Singleton
    class Base @Inject constructor(
        private val provideResources: ProvideResources,
    ) : HandleError<String> {
        override fun handle(e: Exception): String = with(provideResources) {
            if (e is UnknownHostException) noInternetConnectionMessage()
            else serviceUnavailableMessage()
        }

    }

}