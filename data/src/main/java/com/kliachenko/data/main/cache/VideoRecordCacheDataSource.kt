package com.kliachenko.data.main.cache

import javax.inject.Inject

class VideoRecordCacheDataSource {

    interface Save {
        suspend fun save(videoRecords: List<VideoRecordCache>)
    }

    interface Read {
        suspend fun read(): List<VideoRecordCache>
    }

    interface Mutable : Save, Read

    class Base @Inject constructor(
        private val videoRecordDao: VideoRecordDao,
    ) : Mutable {

        constructor(
            dataBase: VideoRecordDataBase,
        ) : this(dataBase.videoRecordDao())

        override suspend fun save(videoRecords: List<VideoRecordCache>) {
            videoRecordDao.saveVideoInfos(videoRecords)
        }

        override suspend fun read() = videoRecordDao.videoInfos()
    }
}