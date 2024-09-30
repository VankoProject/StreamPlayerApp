package com.kliachenko.streamplayerapp.mainPageObject.progress

import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.kliachenko.streamplayerapp.matchers.RecyclerViewMatcher
import com.kliachenko.streamplayerapp.matchers.waitUntilProgressIsNotDisplayed
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class ProgressBarUi(
    parentStateId: Int,
    parentStateClass: Class<RecyclerView>,
) {

    private val progressBarId: Int = com.kliachenko.presentation.R.id.progressBar
    private val progressMatcher = RecyclerViewMatcher(
        position = 0,
        targetViewId = progressBarId,
        recyclerViewId = parentStateId
    )

    private val interaction: ViewInteraction = onView(
        allOf(
            progressMatcher,
            withParent(isAssignableFrom(parentStateClass)),
            withId(progressBarId),
            isAssignableFrom(ProgressBar::class.java)
        )
    )

    fun checkVisible() {
        interaction.check(matches(isDisplayed()))
    }

    fun waitUntilIsNotVisible() {
        onView(isRoot()).perform(waitUntilProgressIsNotDisplayed(progressBarId, 3000))
    }

    fun checkNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
