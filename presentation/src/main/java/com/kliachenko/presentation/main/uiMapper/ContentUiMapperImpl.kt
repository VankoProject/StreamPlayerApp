package com.kliachenko.presentation.main.uiMapper

import com.kliachenko.domain.VideoRecordItem
import com.kliachenko.presentation.main.adapter.ContentUi


class ContentUiMapperImpl : VideoRecordItem.Mapper<ContentUi> {

    override fun mapItem(
        id: Int,
        videoType: String,
        duration: Int,
        tags: String,
        videoUrl: String,
        imageUrl: String,
    ) = ContentUi.VideoRecord(
        id = id,
        videoType = videoType,
        duration = duration,
        tags = tags,
        imageUrl = imageUrl
    )

}