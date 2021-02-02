package com.example.productdetail.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.fragment_product_detail.*


object ImageUtil {

    fun loadImage(context: Context, imageView: ImageView, path: String, goneAble: Boolean = false, imageShadowView: ImageView? = null) {
        Glide.with(context).load(path)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    if (goneAble) {
                        imageView.visibility = View.GONE

                    } else {
                        imageView.visibility = View.INVISIBLE
                    }

                    if (imageShadowView != null) {
                        imageShadowView.visibility = View.GONE
                    }
                    return false
                }
                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    imageView.visibility = View.VISIBLE
                    if (imageShadowView != null) {
                        imageShadowView.visibility = View.VISIBLE
                    }
                    return false
                }
            })
            .into(imageView!!)
    }
}
