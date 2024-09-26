package com.kliachenko.data.mapper

import com.kliachenko.data.main.cache.VideoRecordCache
import com.kliachenko.domain.VideoRecordItem
import javax.inject.Inject

interface VideoRecordMapper<T : Any> {

    fun map(
        id: Int,
        videoType: String,
        duration: Int,
        tags: String,
        videoUrl: String,
        imageUrl: String,
    ): T

    interface ToCache : VideoRecordMapper<VideoRecordCache> {
        class Base @Inject constructor() : ToCache {
            override fun map(
                id: Int,
                videoType: String,
                duration: Int,
                tags: String,
                videoUrl: String,
                imageUrl: String,
            ) = VideoRecordCache(
                id = id,
                videoType = videoType,
                duration = duration,
                tags = tags,
                videoUrl = videoUrl,
                imageUrl = imageUrl,
            )
        }
    }

    interface ToDomain : VideoRecordMapper<VideoRecordItem> {
        class Base @Inject constructor() : ToDomain {
            override fun map(
                id: Int,
                videoType: String,
                duration: Int,
                tags: String,
                videoUrl: String,
                imageUrl: String,
            ) = VideoRecordItem.Base(
                id = id,
                videoType = videoType,
                duration = duration,
                tags = tags,
                videoUrl = videoUrl,
                imageUrl = imageUrl
            )
        }
    }

}