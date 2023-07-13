package com.ewide.test.rasyidr.utilities.extenstion

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ewide.test.rasyidr.R

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
//        .placeholder(R.drawable.loading_anim)
        .error(R.drawable.ic_broken_image)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop()
        .override(150, 100)
        .thumbnail(0.25f)
        .into(this)
}
