package com.kliachenko.presentation.content.adapter

import com.kliachenko.presentation.databinding.ErrorStateLayoutBinding
import com.kliachenko.presentation.databinding.ProgressStateLayoutBinding
import com.kliachenko.presentation.databinding.VideoItemLayoutBinding

interface ContentUi {

    fun show(binding: ErrorStateLayoutBinding) = Unit

    fun show(binding: VideoItemLayoutBinding) = Unit

    fun show(binding: ProgressStateLayoutBinding) = Unit

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

        override fun id(): String = javaClass.simpleName + errorMessage

        override fun show(binding: ErrorStateLayoutBinding) {
            binding.errorTextView.text = errorMessage
        }
    }

    data class VideoRecord(
        private val id: Int,
        private val videoType: String,
        private val duration: Int,
        private val tags: String,
        private val imageUrl: String,
        private val videoUrl: String
    ) : ContentUi {

        override fun type() = ContentUiViewType.VideoRecord

        override fun id(): String = id.toString()

        override fun show(binding: VideoItemLayoutBinding) = with(binding) {
            videoTypeTextView.text = videoType
            durationTextView.show(duration)
            tagsTextView.text = tags
            posterImageView.show(imageUrl)
        }

        override fun videoUrl() = videoUrl

    }

}
