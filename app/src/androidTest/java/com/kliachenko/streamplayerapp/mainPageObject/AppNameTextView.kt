package com.kliachenko.streamplayerapp.mainPageObject

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.android.material.textview.MaterialTextView
import com.kliachenko.streamplayerapp.matchers.ColorMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class AppNameTextView(
    rootLayoutId: Matcher<View>,
    rootParentClass: Matcher<View>,
    uiContext: Context,
) {

    private val textColor =
        ContextCompat.getColor(uiContext, com.kliachenko.presentation.R.color.white)
    private val text =
        uiContext.getText(com.kliachenko.presentation.R.string.app_title_name).toString()
    private val appNameTextView: Int = com.kliachenko.presentation.R.id.appNameTextView
    private val interaction: ViewInteraction = onView(
        allOf(
            rootLayoutId, rootParentClass,
            isAssignableFrom(MaterialTextView::class.java),
            withId(appNameTextView)
        )
    )

    fun checkVisible() {
        interaction.apply {
            check(matches(isDisplayed()))
            check(matches(withText(text)))
            check(matches(ColorMatcher(textColor)))
        }
    }

}
