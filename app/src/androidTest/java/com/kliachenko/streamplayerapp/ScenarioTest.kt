package com.kliachenko.streamplayerapp

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kliachenko.streamplayerapp.mainPageObject.MainPage
import com.kliachenko.streamplayerapp.mainPageObject.success.VideoItem
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
        mainPage.checkSuccessState(
            items = listOf(
                VideoItem(videoType = "film", duration = "13 seconds", tags = "animals"),
                VideoItem(videoType = "animation", duration = "25 seconds", tags = "industry"),
                VideoItem(videoType = "film", duration = "30 seconds", tags = "people")
            )
        )
        activityScenarioRule.scenario.recreate()
        mainPage.checkSuccessState(
            items = listOf(
                VideoItem(videoType = "film", duration = "13 seconds", tags = "animals"),
                VideoItem(videoType = "animation", duration = "25 seconds", tags = "industry"),
                VideoItem(videoType = "film", duration = "30 seconds", tags = "people")
            )
        )
        mainPage.tapItem(position = 0)
        mainPage.checkNotVisible()
    }

}