package com.kliachenko.presentation.main.adapter

import com.kliachenko.presentation.databinding.ErrorStateLayoutBinding
import com.kliachenko.presentation.databinding.VideoItemLayoutBinding

interface ContentUi {

    fun show(binding: ErrorStateLayoutBinding) = Unit

    fun show(binding: VideoItemLayoutBinding) = Unit

    fun type(): ContentUiViewType

    fun id(): String = ""

    fun videoUrl(): String = ""

    object Progress : ContentUi {

        override fun type() = ContentUiViewType.Progress

        override fun id(): String = javaClass.simpleName

    }

    data class Error(
        private val errorMessage: String,
    ) : ContentUi {

        override fun type() = ContentUiViewType.Error

        override fun id(): String = javaClass.simpleName

        override fun show(binding: ErrorStateLayoutBinding) {
            binding.errorTextView.text = errorMessage
        }
    }

    data class VideoRecord(
        private val videoType: String,
        private val duration: Int,
        private val tags: String,
        private val imageUrl: String,
    ) : ContentUi {

        override fun type() = ContentUiViewType.VideoRecord

        override fun id(): String = javaClass.simpleName

        override fun show(binding: VideoItemLayoutBinding) = with(binding) {
            videoTypeTextView.text = videoType
            durationTextView.text = duration.toString()
            tagsTextView.text = tags
            posterImageView.show(imageUrl) // TODO: PicassoWrapper with CustomImageView
        }

    }

}
