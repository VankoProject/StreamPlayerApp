package com.kliachenko.streamplayerapp.core

import com.kliachenko.data.main.cloud.LoadVideoRecordCloudDataSource
import com.kliachenko.data.main.cloud.VideoRecordService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

interface ProvideInstance {

    fun provideLoadVideoRecordCloudDataSource(retrofit: Retrofit): LoadVideoRecordCloudDataSource

    @Singleton
    class Base @Inject constructor() : ProvideInstance {
        override fun provideLoadVideoRecordCloudDataSource(retrofit: Retrofit) =
            LoadVideoRecordCloudDataSource.Base(retrofit.create(VideoRecordService::class.java))
    }

    @Singleton
    class Mock @Inject constructor() : ProvideInstance {
        override fun provideLoadVideoRecordCloudDataSource(retrofit: Retrofit) =
            LoadVideoRecordCloudDataSource.Fake()
    }

}