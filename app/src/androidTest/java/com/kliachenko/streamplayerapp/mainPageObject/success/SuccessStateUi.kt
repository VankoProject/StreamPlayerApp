package com.kliachenko.streamplayerapp.mainPageObject.success

import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.kliachenko.streamplayerapp.matchers.RecyclerViewMatcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class SuccessStateUi(private val parentStateId: Int, private val parentStateClass: Class<RecyclerView>) {

    private val rootLayoutId: Int = com.kliachenko.presentation.R.id.successLayoutId
    private val rootLayoutClass = CardView::class.java
    private val rootLayoutInteraction: ViewInteraction = onView(
        allOf(
            withParent(withId(parentStateId)),
            withParent(isAssignableFrom(parentStateClass)),
            withId(rootLayoutId),
            isAssignableFrom(rootLayoutClass)
        )
    )

    fun checkProgressState() {
        rootLayoutInteraction.check(matches(not(isDisplayed())))
    }

    fun checkErrorState() {
        rootLayoutInteraction.check(matches(not(isDisplayed())))
    }

    fun checkSuccessState(items: List<VideoItem>) {
        items.forEachIndexed { index, videoItem ->
            videoItem.check(index)
        }
    }

    fun tap(position: Int) {
        onView(
            RecyclerViewMatcher(
                position = position,
                targetViewId = rootLayoutId,
                recyclerViewId = parentStateId
            )
        ).perform(click())
    }

}
