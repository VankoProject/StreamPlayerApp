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

class CategoryTextView(rootLayoutId: Matcher<View>, rootParentClass: Matcher<View>, uiContext: Context) {

    private val textColor = ContextCompat.getColor(uiContext,com.kliachenko.presentation.R.color.white)
    private val text = uiContext.getText(com.kliachenko.presentation.R.string.category_name).toString()
    private val categoryTextViewId: Int = com.kliachenko.presentation.R.id.categoryTextView
    private val interaction: ViewInteraction = onView(
        allOf(
            rootLayoutId, rootParentClass,
            isAssignableFrom(MaterialTextView::class.java),
            withId(categoryTextViewId)
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
