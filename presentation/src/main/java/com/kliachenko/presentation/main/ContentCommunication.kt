package com.kliachenko.presentation.main

import com.kliachenko.presentation.core.LiveDataWrapper

interface ContentCommunication: LiveDataWrapper.Mutable<ContentUiState> {

    class Base: LiveDataWrapper.Abstract<ContentUiState>(), ContentCommunication

}