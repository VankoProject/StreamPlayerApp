package com.kliachenko.presentation.customView

import android.content.Context
import android.graphics.PorterDuff
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import com.kliachenko.presentation.R

class CustomPreviousImageButton : AppCompatImageButton {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        setImageResource(R.drawable.previous_active_ic)
    }

    fun showPrevious(hasPrevious: Boolean) {
        isEnabled = hasPrevious
        if (isEnabled) setColorFilter(
            ContextCompat.getColor(context, R.color.white),
            PorterDuff.Mode.SRC_IN
        )
        else setColorFilter(
            ContextCompat.getColor(context, R.color.stoneBrown),
            PorterDuff.Mode.SRC_IN
        )
    }

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            EnableSavedState(it).apply {
                saveIsEnabled(isEnabled)
            }
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        super.onRestoreInstanceState(state)
        if (state is EnableSavedState) {
            isEnabled = state.restoreIsEnabled()
            showPrevious(isEnabled)
        }
    }
}