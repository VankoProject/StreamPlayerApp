package com.kliachenko.domain

interface ContentRepository {

    suspend fun videoRecordsItems(): ContentLoadResult

    suspend fun videoUrlMap(): List<String>

}

interface ContentLoadResult {

    fun <T : Any> map(mapper: Mapper<T>): T

    interface Mapper<T : Any> {

        fun mapSuccess(items: List<VideoRecordItem>): T

        fun mapError(errorMessage: String): T

        fun mapEmpty(): T
    }

    object Empty : ContentLoadResult {
        override fun <T : Any> map(mapper: Mapper<T>): T =
            mapper.mapEmpty()
    }

    data class Error(private val errorMessage: String) : ContentLoadResult {
        override fun <T : Any> map(mapper: Mapper<T>): T =
            mapper.mapError(errorMessage)
    }

    data class Success(private val items: List<VideoRecordItem>) : ContentLoadResult {
        override fun <T : Any> map(mapper: Mapper<T>): T =
            mapper.mapSuccess(items)
    }

}