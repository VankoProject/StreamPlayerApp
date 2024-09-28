//package com.kliachenko.presentation.player
//
//import android.view.View
//import com.kliachenko.presentation.databinding.FragmentVideoPlayerBinding
//
//
//interface PlayerUiState {
//
//    fun update(binding: FragmentVideoPlayerBinding)
//
//    class Play(
//        private val isFirst: Boolean, private val isLast: Boolean, private val isPlaying: Boolean,
//    ) : PlayerUiState {
//        override fun update(
//            binding: FragmentVideoPlayerBinding,
//        ) {
//            binding.playButton.changeStatusIcon(isPlaying)
//            binding.nextVideoButton.show(isLast)
//            binding.previousVideoButton.show(isFirst)
//            binding.videoProgressBar.visibility = View.INVISIBLE
//        }
//    }
//
//    class Stop(
//        private val isFirst: Boolean, private val isLast: Boolean, private val isPlaying: Boolean,
//    ) : PlayerUiState {
//        override fun update(binding: FragmentVideoPlayerBinding) {
//            binding.playButton.changeStatusIcon(isPlaying)
//            binding.nextVideoButton.show(isLast)
//            binding.previousVideoButton.show(isFirst)
//            binding.videoProgressBar.visibility = View.INVISIBLE
//        }
//    }
//
//    class Buffering(
//        private val isFirst: Boolean, private val isLast: Boolean, private val isPlaying: Boolean,
//    ) : PlayerUiState {
//        override fun update(
//            binding: FragmentVideoPlayerBinding,
//        ) {
//            binding.playButton.visibility = View.INVISIBLE
//            binding.nextVideoButton.show(isLast)
//            binding.previousVideoButton.show(isFirst)
//            binding.videoProgressBar.visibility = View.VISIBLE
//        }
//    }
//
//    object Hide : PlayerUiState {
//        override fun update(binding: FragmentVideoPlayerBinding) {
//            binding.PlayerControllerView.visibility = View.INVISIBLE
//        }
//    }
//
//    object Show : PlayerUiState {
//        override fun update(binding: FragmentVideoPlayerBinding) {
//            binding.PlayerControllerView.visibility = View.VISIBLE
//        }
//    }
//
//}