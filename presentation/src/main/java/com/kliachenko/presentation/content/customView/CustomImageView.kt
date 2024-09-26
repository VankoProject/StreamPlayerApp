package com.kliachenko.presentation.content.customView

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.kliachenko.presentation.R
import com.kliachenko.presentation.core.ImageCloudLoader
import com.kliachenko.presentation.core.ProvideImageCloudLoader

class CustomImageView : AppCompatImageView, ShowImage {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val imageCloudLoader: ImageCloudLoader by lazy {
        (context.applicationContext as ProvideImageCloudLoader).imageLoader()
    }

    private var imageUrl: String = ""

    override fun show(imageUrl: String) {
        val width = context.resources.getDimensionPixelSize(R.dimen.poster_width)
        val height = context.resources.getDimensionPixelSize(R.dimen.poster_height)
        imageCloudLoader.show(this, imageUrl, width, height)
        this.imageUrl = imageUrl
    }

}

interface ShowImage {

    fun show(imageUrl: String)
}