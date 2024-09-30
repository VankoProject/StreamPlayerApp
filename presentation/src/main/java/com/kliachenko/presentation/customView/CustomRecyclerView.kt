package com.kliachenko.presentation.customView

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerView : RecyclerView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var layoutManagerSavedState: Parcelable? = null

    override fun setAdapter(adapter: Adapter<*>?) {
        super.setAdapter(adapter)
        restorePosition()
    }

    override fun onSaveInstanceState(): Parcelable {
        val bundle = Bundle().apply {
            putParcelable(SAVED_SUPER_STATE, super.onSaveInstanceState())
            putParcelable(SAVED_LAYOUT_MANAGER, layoutManager?.onSaveInstanceState())
        }
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        var newState = state
        if (newState is Bundle) {
            layoutManagerSavedState =
                newState.getParcelable(SAVED_LAYOUT_MANAGER)
            newState = newState.getParcelable(SAVED_SUPER_STATE)
        }
        super.onRestoreInstanceState(newState)
    }

    private fun restorePosition() {
        layoutManagerSavedState?.let {
            layoutManager?.onRestoreInstanceState(it)
            layoutManagerSavedState = null
        }
    }

    companion object {
        private const val SAVED_SUPER_STATE = "super-state"
        private const val SAVED_LAYOUT_MANAGER = "layout-manager-state"
    }

}