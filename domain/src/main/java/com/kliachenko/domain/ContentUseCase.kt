package com.kliachenko.domain

interface VideoRecordListUserCase {

    suspend fun execute(): ContentLoadResult

    class Base(private val repository: ContentRepository): VideoRecordListUserCase {
        override suspend fun execute() = repository.videoRecordsItems()
    }
}
