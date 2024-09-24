package com.kliachenko.data.main.cloud

import java.net.UnknownHostException
import javax.inject.Inject

interface LoadVideoRecordCloudDataSource {

    suspend fun loadVideoRecords(): TotalResponse

    class Base @Inject constructor(
        private val service: VideoRecordService,
    ) : LoadVideoRecordCloudDataSource {

        override suspend fun loadVideoRecords(): TotalResponse {
            val response = service.videoRecords().execute()
            return response.body()!!
        }
    }


    class Fake @Inject constructor() : LoadVideoRecordCloudDataSource {

        private var firstTime: Boolean = true

        override suspend fun loadVideoRecords(): TotalResponse {
            if (firstTime) {
                firstTime = false
                throw UnknownHostException()
            }
            return TotalResponse(
                listOf(
                    Result(
                        id = 0,
                        videoType = "film",
                        duration = 90,
                        tags = "natural",
                        videos = Videos(
                            large = Large(
                                videoUrl = "https://example.com/videoUrl0",
                                imageUrl = "https://example.com/imageUrl0"
                            )
                        )
                    ),
                    Result(
                        id = 1,
                        videoType = "clip",
                        duration = 120,
                        tags = "urban",
                        videos = Videos(
                            large = Large(
                                videoUrl = "https://example.com/videoUrl1",
                                imageUrl = "https://example.com/imageUrl1"
                            )
                        )
                    ),
                    Result(
                        id = 2,
                        videoType = "series",
                        duration = 180,
                        tags = "comedy",
                        videos = Videos(
                            large = Large(
                                videoUrl = "https://example.com/videoUrl2",
                                imageUrl = "https://example.com/imageUrl2"
                            )
                        )
                    )
                )
            )
        }

    }
}