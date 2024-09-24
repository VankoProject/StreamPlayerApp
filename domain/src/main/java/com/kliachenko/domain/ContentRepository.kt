package com.kliachenko.domain

interface ContentRepository {

    suspend fun contentItems(): ContentLoadResult
}

interface ContentLoadResult {

    fun <T : Any> map(mapper: Mapper<T>): T

    interface Mapper<T : Any> {

        fun mapSuccess(items: List<ContentItem>): T

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

    data class Success(private val items: List<ContentItem>) : ContentLoadResult {
        override fun <T : Any> map(mapper: Mapper<T>): T =
            mapper.mapSuccess(items)
    }

}