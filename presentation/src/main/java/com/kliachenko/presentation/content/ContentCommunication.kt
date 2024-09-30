package com.kliachenko.presentation.content

import com.kliachenko.presentation.core.LiveDataWrapper
import javax.inject.Inject

interface ContentCommunication: LiveDataWrapper.Mutable<ContentUiState> {

    class Base @Inject constructor(): LiveDataWrapper.Abstract<ContentUiState>(),
        ContentCommunication

}