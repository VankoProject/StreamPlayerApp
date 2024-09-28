package com.kliachenko.presentation.player

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.annotation.OptIn
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.kliachenko.presentation.R
import com.kliachenko.presentation.databinding.FragmentVideoPlayerBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class VideoPlayerFragment : Fragment(R.layout.fragment_video_player) {

    private var _binding: FragmentVideoPlayerBinding? = null
    private val binding get() = _binding!!
    private val viewModel: VideoPlayerViewModel by viewModels()

    @Inject
    lateinit var exoPlayer: ExoPlayer
    private lateinit var mediaItems: List<MediaItem>

    private var isControllerVisible = true

    @SuppressLint("ClickableViewAccessibility")
    @OptIn(UnstableApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentVideoPlayerBinding.bind(view)
        val videoUrl = arguments?.getString("videoUrl") ?: ""

        mediaItems = listOf(
            MediaItem.fromUri("https://cdn.pixabay.com/video/2024/08/30/228847_small.mp4"),
            MediaItem.fromUri("https://cdn.pixabay.com/video/2023/07/28/173530-849610807_small.mp4"),
            MediaItem.fromUri("https://cdn.pixabay.com/video/2022/11/22/140111-774507949_small.mp4"),
            MediaItem.fromUri("https://cdn.pixabay.com/video/2023/03/09/153976-817104245_small.mp4"),
            MediaItem.fromUri("https://cdn.pixabay.com/video/2024/09/15/231596_small.mp4")
        )

        exoPlayer.setMediaItems(mediaItems)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true

        val controllerView = binding.customPlayerController

        binding.exoPlayerView.useController = false
        binding.exoPlayerView.player = exoPlayer
        binding.exoPlayerView.controllerShowTimeoutMs = 4000

        controllerView.exoPrev.setOnClickListener {
            exoPlayer.seekToPrevious()
        }

        controllerView.exoNext.setOnClickListener {
            exoPlayer.seekToNext()
        }

        controllerView.exoPlay.setOnClickListener {
            if (exoPlayer.isPlaying) {
                exoPlayer.pause()
                controllerView.exoPlay.changeStatusIcon(exoPlayer.isPlaying)
            } else {
                exoPlayer.play()
                controllerView.exoPlay.changeStatusIcon(exoPlayer.isPlaying)
            }
        }

        binding.root.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                if(!isControllerVisible) {
                    binding.customPlayerController.rootControllerLayout.visibility = View.INVISIBLE
                } else {
                    binding.customPlayerController.rootControllerLayout.visibility = View.VISIBLE
                }
                isControllerVisible = !isControllerVisible
            }
            true
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}