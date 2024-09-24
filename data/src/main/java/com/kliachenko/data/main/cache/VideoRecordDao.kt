package com.kliachenko.data.main.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VideoRecordDao {

    @Query("SELECT * FROM video_data_table")
    suspend fun videoInfos(): List<VideoRecordCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveVideoInfos(videoRecords: List<VideoRecordCache>)

}