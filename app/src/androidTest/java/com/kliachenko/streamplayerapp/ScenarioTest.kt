package com.kliachenko.streamplayerapp

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kliachenko.data.main.cloud.Large
import com.kliachenko.data.main.cloud.Result
import com.kliachenko.data.main.cloud.Videos
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var mainPage: MainPage

    @Before
    fun setup() {
        mainPage = MainPage()
    }

    @Test
    fun load_main_screen_with_error_then_success() {
        mainPage.checkMainProgressState()
        mainPage.waitUntilDashboardProgressStateIsNotVisible()
        mainPage.checkErrorState(errorMessage = "No internet connection")
        activityScenarioRule.scenario.recreate()
        mainPage.checkErrorState(errorMessage = "No internet connection")
        mainPage.tapRetryButton()
        mainPage.checkMainProgressState()
        mainPage.waitUntilDashboardProgressStateIsNotVisible()
        mainPage.checkSuccessfulState(
            category = "Short videos",
            items = listOf(
                VideoItem(videoType = "film", duration = 0.13, tags = "animals"),
                VideoItem(videoType = "animation", duration = 0.25, tags = "industry"),
                VideoItem(videoType = "film", duration = 180, tags = "people")
            )
        )
        activityScenarioRule.scenario.recreate()
        mainPage.checkSuccessfulState(
            category = "Short videos",
            items = listOf(
                VideoItem(videoType = "film", duration = 0.13, tags = "animals"),
                VideoItem(videoType = "animation", duration = 0.25, tags = "industry"),
                VideoItem(videoType = "film", duration = 180, tags = "people")
            )
        )
        mainPage.tapItem(position = 0)
        mainPage.checkNotVisible()
    }

}