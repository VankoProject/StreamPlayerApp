package com.kliachenko.presentation.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.kliachenko.domain.ContentLoadResult
import com.kliachenko.domain.VideoRecordListUserCase
import com.kliachenko.presentation.core.BaseViewModel
import com.kliachenko.presentation.core.Observe
import com.kliachenko.presentation.core.RunAsync
import com.kliachenko.presentation.main.adapter.ClickActions
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val videoRecordListUserCase: VideoRecordListUserCase,
    private val liveDataWrapper: ContentCommunication,
    private val uiMapper: ContentLoadResult.Mapper<ContentUiState>,
    runAsync: RunAsync
): BaseViewModel(runAsync), ClickActions, Observe<ContentUiState> {

    fun init() {
        liveDataWrapper.update(ContentUiState.Progress)
        runAsyncTask({
            videoRecordListUserCase.execute()
        }) {
            liveDataWrapper.update(it.map(uiMapper))
        }
    }

    override fun retry() {
        init()
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<ContentUiState>) {
        liveDataWrapper.observe(owner, observer)
    }

}