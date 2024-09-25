package com.kliachenko.streamplayerapp.mainPageObject.progress

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kliachenko.streamplayerapp.matchers.RecyclerViewMatcher
import org.hamcrest.Matcher

class ProgressStateUi(
    parentStateId: Int,
    parentStateClass: Class<RecyclerView>,
) {

    private val progressBarUi = ProgressBarUi(parentStateId, parentStateClass)

    fun checkProgressState() {
        progressBarUi.checkVisible()
    }

    fun waitUntilIsNotVisible() {
        progressBarUi.waitUntilIsNotVisible()
    }

    fun checkErrorState() {
        progressBarUi.checkNotVisible()
    }

    fun checkSuccessState() {
        progressBarUi.checkNotVisible()
    }

}
