package com.kliachenko.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kliachenko.presentation.databinding.ErrorStateLayoutBinding
import com.kliachenko.presentation.databinding.ProgressStateLayoutBinding
import com.kliachenko.presentation.databinding.VideoItemLayoutBinding

interface ContentUiViewType {

    fun createViewHolder(
        parent: ViewGroup,
        clickActions: ClickActions,
    ): ContentViewHolder

    object Progress : ContentUiViewType {
        override fun createViewHolder(
            parent: ViewGroup,
            clickActions: ClickActions,
        ) = ContentViewHolder.Progress(
            ProgressStateLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    object Error : ContentUiViewType {
        override fun createViewHolder(
            parent: ViewGroup,
            clickActions: ClickActions,
        ) = ContentViewHolder.Error(
            ErrorStateLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), clickActions
        )
    }

    object VideoRecord : ContentUiViewType {
        override fun createViewHolder(
            parent: ViewGroup,
            clickActions: ClickActions,
        ) = ContentViewHolder.VideoRecord(
            VideoItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    companion object {
        fun typeList(): List<ContentUiViewType> = listOf(Progress, Error, VideoRecord)
    }

}