package com.kliachenko.presentation.content

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.kliachenko.domain.ContentLoadResult
import com.kliachenko.domain.VideoRecordListUseCase
import com.kliachenko.presentation.content.adapter.ClickActions
import com.kliachenko.presentation.core.BaseViewModel
import com.kliachenko.presentation.core.Observe
import com.kliachenko.presentation.core.RunAsync
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val videoRecordListUseCase: VideoRecordListUseCase,
    private val communication: ContentCommunication,
    private val uiMapper: ContentLoadResult.Mapper<ContentUiState>,
    runAsync: RunAsync
): BaseViewModel(runAsync), ClickActions, Observe<ContentUiState> {

    fun init() {
        communication.update(ContentUiState.Progress)
        runAsyncTask({
            videoRecordListUseCase.execute()
        }) {
            communication.update(it.map(uiMapper))
        }
    }

    override fun retry() {
        init()
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<ContentUiState>) {
        communication.observe(owner, observer)
    }

}