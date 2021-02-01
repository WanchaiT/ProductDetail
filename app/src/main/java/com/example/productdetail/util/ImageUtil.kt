package com.example.productdetail.util

import android.content.Context
import android.media.Image
import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageUtil {

    fun loadImage(context: Context, imageView: ImageView, path: String) {
        Glide.with(context).load(path).into(imageView!!)
    }
}