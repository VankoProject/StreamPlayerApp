package com.kliachenko.presentation.customView

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textview.MaterialTextView
import com.kliachenko.presentation.R

class CustomDurationTextView: MaterialTextView, ShowDuration {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val secondLabel: String = context.getString(R.string.seconds)

    @SuppressLint("DefaultLocale")
    override fun show(duration: Int) {
       text = String.format("%d %s", duration, secondLabel)
    }

}

interface ShowDuration {

    fun show(duration: Int)
}