package com.kliachenko.presentation.player

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.annotation.OptIn
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.fragment.findNavController
import com.kliachenko.presentation.R
import com.kliachenko.presentation.core.InternetConnectionAvailable
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
    @Inject
    lateinit var internetConnectionAvailable: InternetConnectionAvailable
    private var isControllerVisible = true

    @SuppressLint("ClickableViewAccessibility")
    @OptIn(UnstableApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentVideoPlayerBinding.bind(view)
        val videoUrl = arguments?.getString("videoUrl") ?: ""

        val controllerView = binding.customPlayerController

        binding.exoPlayerView.useController = false
        binding.exoPlayerView.player = exoPlayer
        binding.exoPlayerView.controllerShowTimeoutMs = 4000

        viewModel.init(videoUrl)

        controllerView.exoPrev.setOnClickListener {
            viewModel.playPrevious()
        }

        controllerView.exoNext.setOnClickListener {
            viewModel.playNext()
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

        binding.root.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                if (!isControllerVisible) {
                    controllerView.rootControllerLayout.visibility = View.INVISIBLE
                } else {
                    controllerView.rootControllerLayout.visibility = View.VISIBLE
                }
                isControllerVisible = !isControllerVisible
            }
            true
        }

        viewModel.internetConnection().observe(viewLifecycleOwner) { hasInternet ->
            binding.networkStatusBanner.connectionCardView.visibility =
                if (hasInternet) View.GONE else View.VISIBLE
        }

        viewModel.observe(viewLifecycleOwner) { uiState ->
            uiState.update(binding)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_videoPlayerFragment_to_contentFragment)
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}