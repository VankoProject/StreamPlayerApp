package com.kliachenko.presentation.player

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
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

    @SuppressLint("ClickableViewAccessibility")
    @OptIn(UnstableApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentVideoPlayerBinding.bind(view)
        val videoUrl = arguments?.getString("videoUrl") ?: ""

        with(binding) {
            exoPlayerView.useController = false
            exoPlayerView.player = exoPlayer

            viewModel.init(videoUrl = videoUrl, isFirstRun = savedInstanceState == null)

            customPlayerController.exoPrev.setOnClickListener {
                viewModel.playPrevious()
            }

            customPlayerController.exoNext.setOnClickListener {
                viewModel.playNext()
            }

            customPlayerController.exoPlay.setOnClickListener {
                viewModel.playOrPause()
            }

            serverErrorBanner.retryErrorButton.setOnClickListener {
                findNavController().popBackStack()
            }

            viewModel.internetConnection().observe(viewLifecycleOwner) { hasInternet ->
                networkStatusBanner.connectionCardView.visibility =
                    if (hasInternet) View.GONE else View.VISIBLE
                if (hasInternet) viewModel.startWork() else viewModel.pause()
            }

            viewModel.showController().observe(viewLifecycleOwner) { isVisible ->
                binding.customPlayerController.rootControllerLayout.visibility =
                    if (isVisible) View.VISIBLE else View.GONE
            }

            viewModel.playerState().observe(viewLifecycleOwner) { uiState ->
                uiState.update(binding)
                if(uiState is PlayerUiState.PlayerControl) {
                    binding.rootPlayerLayout.setOnTouchListener { _, event ->
                        if (event.action == MotionEvent.ACTION_DOWN) {
                            if (customPlayerController.rootControllerLayout.visibility == View.VISIBLE) {
                                viewModel.hideController()
                            } else {
                                viewModel.showControllerAndStartTimer()
                            }

                        }
                        true
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}