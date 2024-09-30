package com.kliachenko.presentation.customView

import android.os.Parcel
import android.os.Parcelable
import android.view.View

class EnableSavedState: View.BaseSavedState {

    private var isEnabledState: Boolean = true

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcel: Parcel) : super(parcel) {
        isEnabledState = parcel.readByte() != 0.toByte()
    }

    fun saveIsEnabled(isEnabled: Boolean) {
        isEnabledState = isEnabled
    }

    fun restoreIsEnabled() = isEnabledState

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeByte(if (isEnabledState) 1 else 0)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<EnableSavedState> {
        override fun createFromParcel(source: Parcel) = EnableSavedState(source)
        override fun newArray(size: Int): Array<EnableSavedState?> = arrayOfNulls(size)
    }

}