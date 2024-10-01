package com.kliachenko.streamplayerapp.di.coreModule

import com.kliachenko.data.core.HandleError
import com.kliachenko.data.core.ProvideResources
import com.kliachenko.presentation.content.ContentCommunication
import com.kliachenko.presentation.core.RunAsync
import com.kliachenko.presentation.player.ControllerCommunication
import com.kliachenko.presentation.player.PlayerCommunication
import com.kliachenko.streamplayerapp.core.ProvideInstance
import com.kliachenko.streamplayerapp.core.ProvideResourcesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreModule {

    /**
    In this method, you can replace ProvideInstance.Base with .Mock to run unit tests
     */
    @Binds
    abstract fun bindProvideInstance(provideInstance: ProvideInstance.Base): ProvideInstance

    @Binds
    abstract fun bindRunAsync(runAsync: RunAsync.Base): RunAsync

    @Binds
    abstract fun bindProvideResources(provideResources: ProvideResourcesImpl): ProvideResources

    @Binds
    abstract fun bindHandleError(handleError: HandleError.Base): HandleError<String>

    @Binds
    @Singleton
    abstract fun bindContentCommunication(
        communication: ContentCommunication.Base,
    ): ContentCommunication

    @Binds
    @Singleton
    abstract fun bindPlayerCommunication(
        communication: PlayerCommunication.Base,
    ): PlayerCommunication

    @Binds
    @Singleton
    abstract fun bindControllerCommunication(
        communication: ControllerCommunication.Base,
    ): ControllerCommunication

}