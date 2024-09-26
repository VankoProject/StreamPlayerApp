package com.kliachenko.presentation.content

import com.kliachenko.presentation.content.adapter.ContentUi
import com.kliachenko.presentation.content.adapter.ShowList

interface ContentUiState {

    fun updateAdapter(adapter: ShowList)

    data class Error(private val errorMessage: String) : ContentUiState {
        override fun updateAdapter(adapter: ShowList) {
            adapter.showList(listOf(ContentUi.Error(errorMessage)))
        }

    }

    object Progress : ContentUiState {
        override fun updateAdapter(adapter: ShowList) {
            adapter.showList(listOf(ContentUi.Progress))
        }

    }

    data class VideoRecord(private val itemList: List<ContentUi>) : ContentUiState {
        override fun updateAdapter(adapter: ShowList) {
            adapter.showList(itemList)
        }

    }

}