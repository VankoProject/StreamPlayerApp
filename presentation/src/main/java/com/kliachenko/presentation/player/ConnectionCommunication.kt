package com.kliachenko.presentation.player

import com.kliachenko.presentation.core.LiveDataWrapper
import javax.inject.Inject

interface ConnectionCommunication: LiveDataWrapper.Mutable<Boolean> {

    class Base @Inject constructor() : LiveDataWrapper.Post<Boolean>(), ConnectionCommunication
}
