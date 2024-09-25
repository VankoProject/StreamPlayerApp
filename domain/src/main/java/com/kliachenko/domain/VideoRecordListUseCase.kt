package com.kliachenko.domain

import javax.inject.Inject

interface VideoRecordListUseCase {

    suspend fun execute(): ContentLoadResult

    class Base @Inject constructor(private val repository: ContentRepository): VideoRecordListUseCase {
        override suspend fun execute() = repository.videoRecordsItems()
    }
}
