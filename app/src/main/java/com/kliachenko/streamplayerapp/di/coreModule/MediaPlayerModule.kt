package com.kliachenko.streamplayerapp.di.coreModule

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import com.kliachenko.presentation.player.ExoPlayerWrapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MediaPlayerModule {

    @Binds
    abstract fun bindExoPlayerWrapper(
        exoPlayerWrapper: ExoPlayerWrapper.Base
    ): ExoPlayerWrapper

    companion object {
        @Provides
        @Singleton
        fun provideExoPlayer(@ApplicationContext context: Context): ExoPlayer {
            return ExoPlayer.Builder(context).build()
        }
    }

}