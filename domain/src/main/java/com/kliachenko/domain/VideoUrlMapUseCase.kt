package com.kliachenko.domain

import javax.inject.Inject

interface VideoUrlMapUseCase {

    suspend fun execute(): List<String>

    class Base@Inject constructor(private val repository: ContentRepository): VideoUrlMapUseCase {
        override suspend fun execute() = repository.videoUrlMap()
    }

}