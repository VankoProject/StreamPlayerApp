package com.kliachenko.streamplayerapp.mainPageObject.error

import android.content.Context
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.kliachenko.streamplayerapp.matchers.ColorMatcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class RetryButtonUi(errorLayoutId: Int, errorLayoutClass: Class<LinearLayout>, uiContext: Context) {

    private val textColor = ContextCompat.getColor(uiContext, com.kliachenko.presentation.R.color.white)
    private val buttonText = uiContext.getText(com.kliachenko.presentation.R.string.retry_button).toString()
    private val retryButtonId: Int = com.kliachenko.presentation.R.id.retryButton
    private val interaction: ViewInteraction = onView(
        allOf(
            withParent(withId(errorLayoutId)),
            withParent(isAssignableFrom(errorLayoutClass)),
            isAssignableFrom(MaterialButton::class.java),
            withId(retryButtonId)
        )
    )

    fun checkVisible() {
        interaction.apply {
            check(matches(isDisplayed()))
            check(matches(withText(buttonText)))
            check(matches(ColorMatcher(textColor)))
        }
    }

    fun tap() {
        interaction.perform(click())
    }

    fun checkNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
