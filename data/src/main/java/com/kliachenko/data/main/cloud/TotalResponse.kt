package com.kliachenko.data.main.cloud

import com.google.gson.annotations.SerializedName
import com.kliachenko.data.mapper.MapVideoRecord
import com.kliachenko.data.mapper.VideoRecordMapper

data class TotalResponse(
    @SerializedName("hits")
    val result: List<Result>,
)

data class Result(
    @SerializedName("id")
    val id: Int,
    @SerializedName("type")
    val videoType: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("tags")
    val tags: String,
    @SerializedName("videos")
    val videos: Videos,
) : MapVideoRecord {
    override fun <T : Any> map(mapper: VideoRecordMapper<T>) =
        mapper.map(
            id = id,
            videoType = videoType,
            duration = duration,
            tags = tags,
            videoUrl = videos.large.videoUrl,
            imageUrl = videos.large.imageUrl
        )
}

data class Videos(
    @SerializedName("large")
    val large: Large,
)

data class Large(
    @SerializedName("url")
    val videoUrl: String,
    @SerializedName("thumbnail")
    val imageUrl: String,
)