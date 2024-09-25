package com.kliachenko.streamplayerapp.mainPageObject.success

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.kliachenko.streamplayerapp.matchers.RecyclerViewMatcher

class VideoItem(
    private val videoType: String,
    private val duration: String,
    private val tags: String
) {

    private val recyclerViewId: Int =  com.kliachenko.presentation.R.id.contentRecyclerView

    fun check(index: Int) {
        onView(
            RecyclerViewMatcher(
                position = index,
                targetViewId = com.kliachenko.presentation.R.id.posterImageView,
                recyclerViewId = recyclerViewId
            )
        ).check(matches((isDisplayed())))

        onView(
            RecyclerViewMatcher(
                position = index,
                targetViewId = com.kliachenko.presentation.R.id.tagsTextView,
                recyclerViewId = recyclerViewId
            )
        ).check(matches(withText(tags)))

        onView(
            RecyclerViewMatcher(
                position = index,
                targetViewId = com.kliachenko.presentation.R.id.videoTypeTextView,
                recyclerViewId = recyclerViewId
            )
        ).check(matches(withText(videoType)))

        onView(
            RecyclerViewMatcher(
                position = index,
                targetViewId = com.kliachenko.presentation.R.id.durationTextView,
                recyclerViewId = recyclerViewId
            )
        ).check(matches(withText(duration)))
    }

}
