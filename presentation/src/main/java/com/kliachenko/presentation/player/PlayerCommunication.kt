package com.kliachenko.presentation.player

import com.kliachenko.presentation.core.LiveDataWrapper
import javax.inject.Inject

interface PlayerCommunication : LiveDataWrapper.Mutable<PlayerUiState> {

    class Base @Inject constructor() : LiveDataWrapper.Abstract<PlayerUiState>(),
        PlayerCommunication
}