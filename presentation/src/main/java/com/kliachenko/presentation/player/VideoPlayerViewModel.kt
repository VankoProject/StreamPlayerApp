package com.kliachenko.presentation.player

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.kliachenko.domain.VideoUrlMapUseCase
import com.kliachenko.presentation.core.BaseViewModel
import com.kliachenko.presentation.core.InternetConnectionAvailable
import com.kliachenko.presentation.core.Observe
import com.kliachenko.presentation.core.RunAsync
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoPlayerViewModel @Inject constructor(
    private val videoUrlMapUseCase: VideoUrlMapUseCase,
    private val communication: PlayerCommunication,
    private val internetConnectionAvailable: InternetConnectionAvailable,
    private val connectionCommunication: ConnectionCommunication,
    private val player: ExoPlayerWrapper,
    runAsync: RunAsync,
) : BaseViewModel(runAsync), Observe<PlayerUiState> {

    fun internetConnection() = connectionCommunication.liveData()

    init {
        connectionCommunication.liveData().observeForever { isConnected ->
            if(isConnected) {
                player.startWork()
            } else {
                player.pause()
            }
        }
    }

    private var actualVideoList: List<String> = emptyList()
    private var currentVideoIndex: Int = 0

    fun init(videoUrl: String) {
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

    fun playNext() {
        player.nextVideo()
    }

    fun playPrevious() {
        player.previousVideo()
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<PlayerUiState>) {
        communication.observe(owner, observer)
    }

}