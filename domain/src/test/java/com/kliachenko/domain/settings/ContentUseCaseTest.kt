package com.kliachenko.domain.settings

import com.kliachenko.domain.ContentLoadResult
import com.kliachenko.domain.ContentRepository
import com.kliachenko.domain.VideoRecordItem
import com.kliachenko.domain.VideoRecordListUserCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class ContentUseCaseTest {

    private lateinit var repository: FakeContentRepository
    private lateinit var useCase: VideoRecordListUserCase

    @Before
    fun setup() {
        repository = FakeContentRepository()
        useCase = VideoRecordListUserCase.Base(
            repository = repository
        )
    }

    @Test
    fun `given error state when execute then return error result`() = runBlocking {
        repository.hasError()

        val expected: ContentLoadResult = ContentLoadResult.Error("No internet connection")
        val actual: ContentLoadResult = useCase.execute()

        assertEquals(expected, actual)
    }

    @Test
    fun `given data state when execute then return success result`() = runBlocking {
        repository.hasData()

        val expected: ContentLoadResult = ContentLoadResult.Success(
            listOf(
                VideoRecordItem.Base(id = 0, type = "film", tags = "natural", videoUrl = "url0"),
                VideoRecordItem.Base(id = 1, type = "clip", tags = "dance", videoUrl = "url1")
            )
        )
        val actual: ContentLoadResult = useCase.execute()

        assertEquals(expected, actual)
    }

    private class FakeContentRepository : ContentRepository {

        private var actualResult: ContentLoadResult = ContentLoadResult.Empty

        fun hasError() {
            actualResult = ContentLoadResult.Error("No internet connection")
        }

        fun hasData() {
            actualResult = ContentLoadResult.Success(
                listOf(
                    VideoRecordItem.Base(
                        id = 0,
                        type = "film",
                        tags = "natural",
                        videoUrl = "url0"
                    ),
                    VideoRecordItem.Base(id = 1, type = "clip", tags = "dance", videoUrl = "url1")
                )
            )
        }

        override suspend fun contentItems(): ContentLoadResult {
            return actualResult
        }

    }
}