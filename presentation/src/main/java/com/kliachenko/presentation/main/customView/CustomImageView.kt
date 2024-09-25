package com.kliachenko.presentation.main.customView

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
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
        imageCloudLoader.show(this@CustomImageView, imageUrl)
        this.imageUrl = imageUrl
    }

}

interface ShowImage {

    fun show(imageUrl: String)
}