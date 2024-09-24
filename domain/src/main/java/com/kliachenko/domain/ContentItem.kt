package com.kliachenko.domain

interface ContentItem {

    fun <T : Any> map(mapper: Mapper<T>): T

    interface Mapper<T : Any> : ContentItem {
        fun mapItem(id: Int, type: String, tags: String, videoUrl: String): T
    }

    data class Base(
        private val id: Int,
        private val type: String,
        private val tags: String,
        private val videoUrl: String,
    ) : ContentItem {
        override fun <T : Any> map(mapper: Mapper<T>): T =
            mapper.mapItem(id = id, type = type, tags = tags, videoUrl = videoUrl)
    }
}


