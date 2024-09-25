package com.kliachenko.streamplayerapp.di.coreModule

import android.content.Context
import androidx.room.Room
import com.kliachenko.data.main.cache.VideoRecordDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): VideoRecordDataBase =
        Room.databaseBuilder(
            context, VideoRecordDataBase::class.java, DB_NAME
        ).build()

    companion object {
        private const val DB_NAME = "video_records_db"
    }

    @Provides
    @Singleton
    fun provideVideoRecordDao(dataBase: VideoRecordDataBase) = dataBase.videoRecordDao()
}