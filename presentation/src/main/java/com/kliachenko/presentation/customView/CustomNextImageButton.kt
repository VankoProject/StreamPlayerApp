package com.kliachenko.presentation.customView

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton

class CustomNextImageButton : AppCompatImageButton {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun show(isLast: Boolean) {
        isEnabled = !isLast
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
        }
    }
}