package com.kliachenko.presentation.player

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import javax.inject.Inject

interface ExoPlayerWrapper {

    fun setMediaItems(items: List<String>)

    fun nextVideo()

    fun previousVideo()

    fun playCurrent(position: Int)

    fun play()

    fun playerStateListener(listener: (isPlaying: Boolean, isBuffering: Boolean) -> Unit)

    fun pause()

    fun startWork()

    class Base @Inject constructor(
        private val exoPlayer: ExoPlayer,
    ) : ExoPlayerWrapper {

        private var mediaList = emptyList<MediaItem>()
        private var currentPosition: Int = 0

        override fun setMediaItems(items: List<String>) {
            mediaList = items.map { MediaItem.fromUri(it) }
            exoPlayer.setMediaItems(mediaList)
            exoPlayer.prepare()
        }

        override fun nextVideo() {
            exoPlayer.seekToNext()
        }

        override fun previousVideo() {
            if (exoPlayer.hasPreviousMediaItem()) {
                exoPlayer.seekToPrevious()
            }
        }

        override fun playCurrent(position: Int) {
            if (position < mediaList.size) {
                currentPosition = position
                exoPlayer.seekTo(position, 0)
                exoPlayer.playWhenReady = true
            }
        }

        override fun play() {
            exoPlayer.play()
        }

        override fun playerStateListener(listener: (isPlaying: Boolean, isBuffering: Boolean) -> Unit) {
            exoPlayer.addListener(object : Player.Listener{
                override fun onPlaybackStateChanged(playbackState: Int) {
                    when(playbackState) {
                        Player.STATE_BUFFERING -> (listener(false, true))
                        Player.STATE_READY -> listener(true, false)
                    }
                }

                override fun onIsPlayingChanged(isPlaying: Boolean) {

                }
            })
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

    }
}