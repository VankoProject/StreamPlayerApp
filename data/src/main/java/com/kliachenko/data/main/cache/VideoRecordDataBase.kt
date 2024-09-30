package com.kliachenko.data.main.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [VideoRecordCache::class], version = 1, exportSchema = false)
abstract class VideoRecordDataBase : RoomDatabase() {

    abstract fun videoRecordDao(): VideoRecordDao
}