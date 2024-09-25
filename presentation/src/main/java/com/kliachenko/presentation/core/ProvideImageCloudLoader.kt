package com.kliachenko.presentation.core

import android.widget.ImageView
import com.squareup.picasso.Picasso

interface ImageCloudLoader {

    fun show(imageView: ImageView, imageUrl: String)

    class Base : ImageCloudLoader {
        override fun show(imageView: ImageView, imageUrl: String) {
            Picasso.get().load(imageUrl).into(imageView)
        }
    }

}


interface ProvideImageCloudLoader {

    fun imageLoader(): ImageCloudLoader
}