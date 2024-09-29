package com.kliachenko.streamplayerapp.di.coreModule

import com.kliachenko.domain.VideoRecordListUseCase
import com.kliachenko.domain.VideoUrlMapUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindVideoRecordListUseCase(
        userCase: VideoRecordListUseCase.Base,
    ): VideoRecordListUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindVideoUrlMapUseCase(
        useCase: VideoUrlMapUseCase.Base
    ): VideoUrlMapUseCase

}