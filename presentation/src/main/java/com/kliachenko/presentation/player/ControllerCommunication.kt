package com.kliachenko.presentation.player

import com.kliachenko.presentation.core.LiveDataWrapper
import javax.inject.Inject

interface ControllerCommunication : LiveDataWrapper.Mutable<Boolean> {

    class Base @Inject constructor() : LiveDataWrapper.Abstract<Boolean>(), ControllerCommunication
}