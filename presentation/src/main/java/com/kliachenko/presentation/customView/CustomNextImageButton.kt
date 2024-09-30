package com.kliachenko.presentation.customView

import android.content.Context
import android.graphics.PorterDuff
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import com.kliachenko.presentation.R

class CustomNextImageButton : AppCompatImageButton {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        setColorFilter(ContextCompat.getColor(context, R.color.white), PorterDuff.Mode.SRC_IN)
    }

    private var currentIconResId: Int = R.drawable.next_active_ic

    fun showNext(hasNext: Boolean) {
        currentIconResId = if (hasNext) R.drawable.next_active_ic else R.drawable.next_disable_ic
        setImageResource(currentIconResId)
        isEnabled = hasNext
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
            showNext(isEnabled)
        }
    }
}