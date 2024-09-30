package com.kliachenko.presentation.core

import com.kliachenko.presentation.customView.CustomImageView
import com.squareup.picasso.Picasso

interface ImageCloudLoader {

    fun show(imageView: CustomImageView, imageUrl: String, width: Int, height: Int)

    class Base : ImageCloudLoader {
        override fun show(imageView: CustomImageView, imageUrl: String, width: Int, height: Int) {
            Picasso.get()
                .load(imageUrl)
                .resize(
                    width, height
                )
                .into(imageView)
        }
    }

}

interface ProvideImageCloudLoader {

    fun imageLoader(): ImageCloudLoader
}