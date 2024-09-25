package com.kliachenko.streamplayerapp.core

import android.content.Context
import com.kliachenko.data.core.ProvideResources
import com.kliachenko.streamplayerapp.R
import javax.inject.Inject

class ProvideResourcesImpl @Inject constructor(
    private val context: Context
): ProvideResources {

    override fun noInternetConnectionMessage() =
        context.getString(R.string.no_internet_connection)

    override fun serviceUnavailableMessage() =
        context.getString(R.string.service_unavailable)
}