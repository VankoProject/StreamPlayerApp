package com.kliachenko.domain

interface MovieListUserCase {

    suspend fun execute(): ContentLoadResult

    class Base(private val repository: ContentRepository): MovieListUserCase {
        override suspend fun execute() = repository.contentItems()
    }
}
