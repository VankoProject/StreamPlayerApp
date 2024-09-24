package com.kliachenko.data.main.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kliachenko.data.mapper.MapVideoRecord
import com.kliachenko.data.mapper.VideoRecordMapper

@Entity(tableName = "video_data_table")
data class VideoRecordCache(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo(name = "video_type")
    val videoType: String,
    @ColumnInfo(name = "duration")
    val duration: Int,
    @ColumnInfo(name = "tags")
    val tags: String,
    @ColumnInfo(name = "video_url")
    val videoUrl: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
) : MapVideoRecord {
    override fun <T : Any> map(mapper: VideoRecordMapper<T>): T =
        mapper.map(id, videoType, duration, tags, videoUrl, imageUrl)
}
