package com.kliachenko.presentation.player

import android.view.View
import com.kliachenko.presentation.databinding.FragmentVideoPlayerBinding

interface PlayerUiState {

    fun update(binding: FragmentVideoPlayerBinding)

    object Progress : PlayerUiState {
        override fun update(binding: FragmentVideoPlayerBinding) = with(binding) {
            customPlayerController.rootControllerLayout.visibility = View.INVISIBLE
            serverErrorBanner.rootErrorPlayerLayout.visibility = View.INVISIBLE
            playerProgressBar.visibility = View.VISIBLE
            customPlayerController.rootControllerLayout.isClickable = false
        }
    }

    data class Notification(private val message: String) : PlayerUiState {
        override fun update(binding: FragmentVideoPlayerBinding) = with(binding) {
            serverErrorBanner.errorServerTextView.changeText(message)
            serverErrorBanner.rootErrorPlayerLayout.visibility = View.VISIBLE
            customPlayerController.rootControllerLayout.visibility = View.GONE
            playerProgressBar.visibility = View.GONE
            customPlayerController.rootControllerLayout.isClickable = false
        }
    }

    data class PlayerControl(
        private val hasNext: Boolean,
        private val hasPrevious: Boolean,
        private val isPlaying: Boolean,
    ) : PlayerUiState {
        override fun update(binding: FragmentVideoPlayerBinding) = with(binding) {
            serverErrorBanner.rootErrorPlayerLayout.visibility = View.GONE
            playerProgressBar.visibility = View.GONE
            with(customPlayerController) {
                rootPlayerLayout.visibility = View.VISIBLE
                exoPlay.changeStatusIcon(playing = isPlaying)
                exoNext.showNext(hasNext)
                exoPrev.showPrevious(hasPrevious)
            }
        }
    }

}