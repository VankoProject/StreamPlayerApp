package com.kliachenko.presentation.main.uiMapper

import com.kliachenko.domain.ContentLoadResult
import com.kliachenko.domain.VideoRecordItem
import com.kliachenko.presentation.main.ContentUiState

class ContentLoadResultMapperImpl(
    private val uiMapper: ContentUiMapperImpl,
) : ContentLoadResult.Mapper<ContentUiState> {

    override fun mapSuccess(items: List<VideoRecordItem>): ContentUiState {
        val uiItems = items.map { domainModel ->
            domainModel.map(uiMapper)
        }
        return ContentUiState.VideoRecord(uiItems)
    }

    override fun mapError(errorMessage: String) = ContentUiState.Error(errorMessage)

    override fun mapEmpty() = ContentUiState.Progress

}