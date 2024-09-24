package com.kliachenko.data.main

import com.kliachenko.data.core.HandleError
import com.kliachenko.data.main.cache.VideoRecordCacheDataSource
import com.kliachenko.data.main.cloud.LoadVideoRecordCloudDataSource
import com.kliachenko.data.main.cloud.TotalResponse
import com.kliachenko.data.mapper.VideoRecordMapper
import com.kliachenko.domain.ContentLoadResult
import com.kliachenko.domain.ContentRepository
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val cacheDataSource: VideoRecordCacheDataSource.Mutable,
    private val loadVideoRecordCloudDataSource: LoadVideoRecordCloudDataSource,
    private val mapToCache: VideoRecordMapper.ToCache,
    private val mapToDomain: VideoRecordMapper.ToDomain,
    private val handleError: HandleError<String>,
) : ContentRepository {

    override suspend fun videoRecordsItems(): ContentLoadResult {
        return try {
            val cacheData = cacheDataSource.read()
            if (cacheData.isNotEmpty()) {
                ContentLoadResult.Success(cacheData.map { it.map(mapToDomain) })
            } else {
                val response: TotalResponse = loadVideoRecordCloudDataSource.loadVideoRecords()
                val videoRecords = response.result
                cacheDataSource.save(videoRecords.map { it.map(mapToCache) })
                val loadResult = videoRecords.map { it.map(mapToDomain) }
                ContentLoadResult.Success(loadResult)
            }
        } catch (e: Exception) {
            ContentLoadResult.Error(handleError.handle(e))
        }

    }
}