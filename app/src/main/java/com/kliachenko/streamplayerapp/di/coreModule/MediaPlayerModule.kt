package com.kliachenko.streamplayerapp.di.coreModule

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import com.kliachenko.presentation.player.ExoPlayerWrapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class MediaPlayerModule {

    @Binds
    abstract fun bindExoPlayerWrapper(
        exoPlayer: ExoPlayerWrapper.Base
    ): ExoPlayerWrapper

    companion object {
        @Provides
        fun provideExoPlayer(@ApplicationContext context: Context): ExoPlayer {
            return ExoPlayer.Builder(context).build()
        }
    }

}