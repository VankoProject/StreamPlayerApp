package com.kliachenko.presentation.player

import androidx.lifecycle.viewModelScope
import com.kliachenko.domain.VideoUrlMapUseCase
import com.kliachenko.presentation.core.BaseViewModel
import com.kliachenko.presentation.core.InternetConnectionAvailable
import com.kliachenko.presentation.core.RunAsync
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class VideoPlayerViewModel @Inject constructor(
    private val videoUrlMapUseCase: VideoUrlMapUseCase,
    private val communication: PlayerCommunication,
    private val internetConnectionAvailable: InternetConnectionAvailable,
    private val connectionCommunication: ConnectionCommunication,
    private val controllerCommunication: ControllerCommunication,
    private val player: ExoPlayerWrapper,
    private val runAsync: RunAsync,
) : BaseViewModel(runAsync) {

    private var controllerJob: Job? = null

    fun internetConnection() = connectionCommunication.liveData()

    fun playerState() = communication.liveData()

    fun showController() = controllerCommunication.liveData()

    private var actualVideoList: List<String> = emptyList()
    private var currentVideoIndex: Int = 0

    fun init(videoUrl: String, isFirstRun: Boolean) {
        if (isFirstRun) {
            if (actualVideoList.isEmpty()) {
                runAsyncTask({
                    videoUrlMapUseCase.execute()
                }) { urls ->
                    actualVideoList = urls
                    player.setMediaItems(actualVideoList)
                    currentVideoIndex = actualVideoList.indexOf(videoUrl)
                    player.playCurrent(currentVideoIndex)
                }
            } else {
                player.playCurrent(currentVideoIndex)
            }
        }
    }

    fun showControllerAndStartTimer() {
        controllerJob?.cancel()
        controllerCommunication.update(value = true)
        controllerJob = runAsync.startAsyncTask(
            coroutineScope = viewModelScope,
            background = { delay(5000) },
            uiBlock = {
                controllerCommunication.update(value = false)
            })
    }

    fun hideController() {
        controllerJob?.cancel()
        controllerCommunication.update(value = false)
    }

    fun playNext() {
        player.nextVideo()
    }

    fun playPrevious() {
        player.previousVideo()
    }

    fun playOrPause() {
        player.playOrPause()
        showControllerAndStartTimer()
    }

    fun startWork() {
        player.startWork()
    }

    fun pause() {
        player.pause()
    }

}