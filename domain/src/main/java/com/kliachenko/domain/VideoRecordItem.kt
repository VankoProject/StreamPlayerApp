package com.kliachenko.domain

interface VideoRecordItem {

    fun <T : Any> map(mapper: Mapper<T>): T

    interface Mapper<T : Any> {
        fun mapItem(
            id: Int,
            videoType: String,
            duration: Int,
            tags: String,
            videoUrl: String,
            imageUrl: String,
        ): T
    }
}

data class Base(
    private val id: Int,
    private val videoType: String,
    private val duration: Int,
    private val tags: String,
    private val videoUrl: String,
    private val imageUrl: String,
) : VideoRecordItem {
    override fun <T : Any> map(mapper: VideoRecordItem.Mapper<T>): T =
        mapper.mapItem(
            id = id,
            videoType = videoType,
            duration = duration,
            tags = tags,
            videoUrl = videoUrl,
            imageUrl = imageUrl
        )
}



