package com.kliachenko.streamplayerapp.di.coreModule

import android.content.Context
import com.kliachenko.presentation.core.InternetConnectionAvailable
import com.kliachenko.presentation.player.ConnectionCommunication
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConnectionModule {

    @Binds
    @Singleton
    abstract fun bindConnectionCommunication(
        communication: ConnectionCommunication.Base,
    ): ConnectionCommunication


    companion object {
        @Provides
        @Singleton
        fun provideInternetConnectionAvailable(
            communication: ConnectionCommunication,
            @ApplicationContext context: Context,
        ): InternetConnectionAvailable {
            return InternetConnectionAvailable(communication, context)
        }
    }

}