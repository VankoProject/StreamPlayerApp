package com.kliachenko.streamplayerapp.mainPageObject.error

import android.content.Context
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.kliachenko.streamplayerapp.matchers.RecyclerViewMatcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class ErrorStateUi(
    parentStateId: Int,
    parentStateClass: Class<RecyclerView>,
    uiContext: Context,
) {

    private val errorLayoutId: Int = com.kliachenko.presentation.R.id.errorRootlayout
    private val errorLayoutClass = LinearLayout::class.java
    private val errorTextUi = ErrorTextUi(errorLayoutId, errorLayoutClass, uiContext)
    private val retryButtonUi = RetryButtonUi(errorLayoutId, errorLayoutClass, uiContext)

    private val errorStateMatcher = RecyclerViewMatcher(
        position = 0,
        targetViewId = errorLayoutId,
        recyclerViewId = parentStateId
    )

    private val rootInteraction = onView(
        allOf(
            withParent(isAssignableFrom(parentStateClass)),
            withId(errorLayoutId),
            isAssignableFrom(errorLayoutClass),
            errorStateMatcher,
        )
    )

    fun checkProgressState() {
        rootInteraction.check(matches(isDisplayed()))
        errorTextUi.checkNotVisible()
        retryButtonUi.checkNotVisible()
    }

    fun checkErrorState(errorMessage: String) {
        rootInteraction.check(matches(isDisplayed()))
        errorTextUi.checkVisible(message = errorMessage)
        retryButtonUi.checkVisible()
    }

    fun tap() {
        retryButtonUi.tap()
    }

    fun checkSuccessState() {
        rootInteraction.check(matches(not(isDisplayed())))
        errorTextUi.checkNotVisible()
        retryButtonUi.checkNotVisible()
    }

}
