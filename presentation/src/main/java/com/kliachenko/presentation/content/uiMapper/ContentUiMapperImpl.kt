package com.kliachenko.presentation.content.uiMapper

import com.kliachenko.domain.VideoRecordItem
import com.kliachenko.presentation.content.adapter.ContentUi
import javax.inject.Inject


class ContentUiMapperImpl @Inject constructor() : VideoRecordItem.Mapper<ContentUi> {

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