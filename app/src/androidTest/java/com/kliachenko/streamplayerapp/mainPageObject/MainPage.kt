package com.kliachenko.streamplayerapp.mainPageObject

import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.platform.app.InstrumentationRegistry
import com.kliachenko.streamplayerapp.mainPageObject.error.ErrorStateUi
import com.kliachenko.streamplayerapp.mainPageObject.progress.ProgressStateUi
import com.kliachenko.streamplayerapp.mainPageObject.success.SuccessStateUi
import com.kliachenko.streamplayerapp.mainPageObject.success.VideoItem
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class MainPage {

    private val uiContext = InstrumentationRegistry.getInstrumentation().targetContext

    private val rootLayoutId: Matcher<View> =
        withParent(withId(com.kliachenko.presentation.R.id.rootLayout))
    private val rootParentClass: Matcher<View> = withParent(isAssignableFrom(LinearLayout::class.java))
    private val rootInteraction: ViewInteraction = onView(allOf(rootLayoutId, rootParentClass))

    private val parentStateId: Int = com.kliachenko.presentation.R.id.contentRecyclerView
    private val parentStateClass = RecyclerView::class.java

    private val errorStateUi = ErrorStateUi(parentStateId, parentStateClass, uiContext)
    private val progressStateUi = ProgressStateUi(parentStateId, parentStateClass)
    private val successStateUi = SuccessStateUi(parentStateId, parentStateClass)

    private val appNameTextView = AppNameTextView(rootLayoutId, rootParentClass, uiContext)
    private val categoryTextView = CategoryTextView(rootLayoutId, rootParentClass, uiContext)

    fun checkMainProgressState() {
        appNameTextView.checkVisible()
        categoryTextView.checkVisible()
        errorStateUi.checkProgressState()
        progressStateUi.checkProgressState()
        successStateUi.checkProgressState()
    }

    fun waitUntilDashboardProgressStateIsNotVisible() {
        progressStateUi.waitUntilIsNotVisible()
    }

    fun checkErrorState(errorMessage: String) {
        appNameTextView.checkVisible()
        categoryTextView.checkVisible()
        errorStateUi.checkErrorState(errorMessage = errorMessage)
        progressStateUi.checkErrorState()
        successStateUi.checkErrorState()
    }

    fun tapRetryButton() {
        errorStateUi.tap()
    }

    fun checkSuccessState(items: List<VideoItem>) {
        appNameTextView.checkVisible()
        categoryTextView.checkVisible()
        errorStateUi.checkSuccessState()
        progressStateUi.checkSuccessState()
        successStateUi.checkSuccessState(items = items)
    }

    fun tapItem(position: Int) {
        successStateUi.tap(position)
    }

    fun checkNotVisible() {
        rootInteraction.check(matches(not(isDisplayed())))
    }

}
