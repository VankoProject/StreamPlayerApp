package com.kliachenko.streamplayerapp

import android.app.Application
import com.kliachenko.presentation.core.ImageCloudLoader
import com.kliachenko.presentation.core.ProvideImageCloudLoader
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(), ProvideImageCloudLoader {

    private lateinit var imageCloudLoader: ImageCloudLoader

    override fun onCreate() {
        super.onCreate()
        imageCloudLoader = ImageCloudLoader.Base()
    }

    override fun imageLoader() = imageCloudLoader
}