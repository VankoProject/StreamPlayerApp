package com.kliachenko.presentation.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

abstract class BaseViewModel (
    private val runAsync: RunAsync,
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    protected fun <T : Any> runAsyncTask(
        background: suspend () -> T,
        uiBlock: (T) -> Unit,
    ) {
        runAsync.startAsyncTask(viewModelScope, background = background, uiBlock = uiBlock)
    }
}


interface RunAsync {

    fun <T : Any> startAsyncTask(
        coroutineScope: CoroutineScope,
        background: suspend () -> T,
        uiBlock: (T) -> Unit,
        job: Job? = null
    ): Job

    class Base @Inject constructor(): RunAsync {
        override fun <T : Any> startAsyncTask(
            coroutineScope: CoroutineScope,
            background: suspend () -> T,
            uiBlock: (T) -> Unit,
            job: Job?
        ): Job {

            job?.cancel()

            val newJob = coroutineScope.launch(Dispatchers.IO) {
                val result = background.invoke()
                withContext(Dispatchers.Main) {
                    uiBlock.invoke(result)
                }
            }
            return newJob
        }
    }

}