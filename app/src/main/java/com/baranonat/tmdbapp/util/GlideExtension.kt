package com.baranonat.tmdbapp.util

import android.widget.ImageView
import com.baranonat.tmdbapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.centerCropTransform


fun ImageView.loadCircleImage(path:String?){
    Glide.with(this.context).load(Constans.IMAGE_BASE_URL+path).apply(centerCropTransform().error(R.drawable.ic_error).circleCrop()).into(this)


}


fun ImageView.loadImage(path:String?){
    Glide.with(this.context).load(Constans.IMAGE_BASE_URL+path).apply(centerCropTransform().error(R.drawable.ic_error)).into(this)


}