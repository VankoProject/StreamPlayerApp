package com.kliachenko.presentation.customView

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import com.kliachenko.presentation.R

class CustomPlayImageButton : AppCompatImageButton {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var currentIconResId: Int = R.drawable.pause_ic

    fun changeStatusIcon(playing: Boolean) {
        currentIconResId = if (playing) R.drawable.pause_ic else R.drawable.play_ic
        setImageResource(currentIconResId)
    }

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            IdSavedState(it).apply {
                saveResId(currentIconResId)
            }
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        super.onRestoreInstanceState(state)
        if (state is IdSavedState) {
            currentIconResId = state.restoreResId()
            setImageResource(currentIconResId)
        }
    }

}