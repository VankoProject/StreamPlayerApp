package com.kliachenko.streamplayerapp.mainPageObject.error

import android.content.Context
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.google.android.material.textview.MaterialTextView
import com.kliachenko.streamplayerapp.matchers.ColorMatcher
import com.kliachenko.streamplayerapp.matchers.TextMatcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class ErrorTextUi(errorLayoutId: Int, errorLayoutClass: Class<LinearLayout>, uiContext: Context) {

    private val errorTextColor =
        ContextCompat.getColor(uiContext, com.kliachenko.presentation.R.color.error)
    private val errorTextViewId: Int = com.kliachenko.presentation.R.id.errorTextView
    private val interaction: ViewInteraction = onView(
        allOf(
            withParent(withId(errorLayoutId)),
            withParent(isAssignableFrom(errorLayoutClass)),
            isAssignableFrom(MaterialTextView::class.java),
            withId(errorTextViewId)
        )
    )

    fun checkVisible(message: String) {
        interaction.apply {
            check(matches(isDisplayed()))
            check(matches(TextMatcher(message)))
            check(matches(ColorMatcher(errorTextColor)))
        }
    }

    fun checkNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
