package com.kliachenko.presentation.player

import android.annotation.SuppressLint
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.PlaybackException.ERROR_CODE_IO_BAD_HTTP_STATUS
import androidx.media3.common.PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED
import androidx.media3.common.PlaybackException.ERROR_CODE_TIMEOUT
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import javax.inject.Inject

interface ExoPlayerWrapper {

    fun setMediaItems(items: List<String>)

    fun nextVideo()

    fun previousVideo()

    fun playCurrent(position: Int)

    fun updatePlayUiState(isPlaying: Boolean)

    fun pause()

    fun startWork()

    fun playOrPause()

    class Base @Inject constructor(
        private val exoPlayer: ExoPlayer,
        private val communication: PlayerCommunication,
    ) : ExoPlayerWrapper {

        private var mediaList = emptyList<MediaItem>()

        private val playerStateListener = object : Player.Listener {
            @SuppressLint("SwitchIntDef")
            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    Player.STATE_BUFFERING -> {
                        communication.update(PlayerUiState.Progress)
                    }

                    Player.STATE_READY -> {
                        communication.update(
                            PlayerUiState.PlayerControl(
                                isPlaying = exoPlayer.isPlaying,
                                hasNext = exoPlayer.hasNextMediaItem(),
                                hasPrevious = exoPlayer.hasPreviousMediaItem()
                            )
                        )
                    }

                    Player.STATE_ENDED -> {
                        if (exoPlayer.repeatMode == ExoPlayer.REPEAT_MODE_ALL) {
                            if (exoPlayer.hasNextMediaItem()) {
                                exoPlayer.seekToNext()
                                exoPlayer.play()
                            } else
                                communication.update(PlayerUiState.Notification("End of playlist."))
                        }
                    }
                }
            }

            override fun onPlayerError(error: PlaybackException) {
                val notificationMessage = when (error.errorCode) {
                    ERROR_CODE_TIMEOUT,
                    ERROR_CODE_IO_NETWORK_CONNECTION_FAILED,
                    ERROR_CODE_IO_BAD_HTTP_STATUS,
                    -> error.message?.takeIf { it.isNotBlank() }
                        ?: "An unknown error occurred."

                    else -> "Your request limit has been exceeded. Please try again later."
                }
                communication.update(PlayerUiState.Notification(notificationMessage))
            }
        }

        init {
            exoPlayer.addListener(playerStateListener)
        }

        override fun setMediaItems(items: List<String>) {
            mediaList = items.map { MediaItem.fromUri(it) }
            exoPlayer.setMediaItems(mediaList)
            exoPlayer.prepare()
        }

        override fun pause() {
            exoPlayer.pause()
        }

        override fun startWork() {
            exoPlayer.apply {
                prepare()
                playWhenReady = true
            }
        }

        override fun playOrPause() {
            if (exoPlayer.isPlaying) {
                exoPlayer.pause()
            } else {
                exoPlayer.play()
            }
            updatePlayUiState(exoPlayer.isPlaying)
        }

        override fun nextVideo() {
            if (exoPlayer.hasNextMediaItem())
                exoPlayer.seekToNext()
            exoPlayer.playWhenReady = true
        }

        override fun previousVideo() {
            if (exoPlayer.hasPreviousMediaItem())
                exoPlayer.seekToPrevious()
            exoPlayer.playWhenReady = true
        }

        override fun playCurrent(position: Int) {
            if (position in mediaList.indices)
                exoPlayer.seekTo(position, 0)
            exoPlayer.playWhenReady = true
        }

        override fun updatePlayUiState(isPlaying: Boolean) {
            communication.update(
                PlayerUiState.PlayerControl(
                    isPlaying = exoPlayer.isPlaying,
                    hasNext = exoPlayer.hasNextMediaItem(),
                    hasPrevious = exoPlayer.hasPreviousMediaItem()
                )
            )
        }

    }
}