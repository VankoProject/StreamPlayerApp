package com.kliachenko.data.mapper

interface MapVideoRecord {

    fun <T: Any> map(mapper: VideoRecordMapper<T>): T
}