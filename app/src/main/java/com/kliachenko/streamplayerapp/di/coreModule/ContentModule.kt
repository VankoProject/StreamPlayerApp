package com.kliachenko.streamplayerapp.di.coreModule

import com.kliachenko.data.core.HandleError
import com.kliachenko.data.main.ContentRepositoryImpl
import com.kliachenko.data.main.cache.VideoRecordCacheDataSource
import com.kliachenko.data.main.cloud.LoadVideoRecordCloudDataSource
import com.kliachenko.data.mapper.VideoRecordMapper
import com.kliachenko.domain.ContentRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ContentModule {

    @Binds
    abstract fun bindVideoRecordCacheDataSource(
        cacheDataSource: VideoRecordCacheDataSource.Base,
    ): VideoRecordCacheDataSource.Mutable

    @Binds
    abstract fun bindLoadVideoRecordCloudDataSource(
        cloudDataSource: LoadVideoRecordCloudDataSource.Base,
    ): LoadVideoRecordCloudDataSource

    @Binds
    abstract fun bindVideoRecordMapperToCache(
        mapper: VideoRecordMapper.ToCache.Base,
    ): VideoRecordMapper.ToCache

    @Binds
    abstract fun bindVideoRecordMapperToDomain(
        mapper: VideoRecordMapper.ToDomain.Base,
    ): VideoRecordMapper.ToDomain


    companion object {
        @Provides
        fun provideRepository(
            cacheDataSource: VideoRecordCacheDataSource.Mutable,
            loadVideoRecordCloudDataSource: LoadVideoRecordCloudDataSource,
            mapToCache: VideoRecordMapper.ToCache,
            mapToDomain: VideoRecordMapper.ToDomain,
            handleError: HandleError<String>,
        ): ContentRepository = ContentRepositoryImpl(
            cacheDataSource, loadVideoRecordCloudDataSource, mapToCache, mapToDomain, handleError
        )
    }
}