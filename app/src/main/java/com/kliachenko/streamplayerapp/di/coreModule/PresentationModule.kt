package com.kliachenko.streamplayerapp.di.coreModule

import com.kliachenko.domain.ContentLoadResult
import com.kliachenko.domain.VideoRecordItem
import com.kliachenko.presentation.content.ContentUiState
import com.kliachenko.presentation.content.adapter.ContentUi
import com.kliachenko.presentation.content.uiMapper.ContentLoadResultMapperImpl
import com.kliachenko.presentation.content.uiMapper.ContentUiMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class PresentationModule {

    @Binds
    @ViewModelScoped
    abstract fun bindContentLoadResultMapper(
        mapper: ContentLoadResultMapperImpl,
    ): ContentLoadResult.Mapper<ContentUiState>

    @Binds
    @ViewModelScoped
    abstract fun bindContentUiMapper(
        mapper: ContentUiMapperImpl,
    ): VideoRecordItem.Mapper<ContentUi>

}