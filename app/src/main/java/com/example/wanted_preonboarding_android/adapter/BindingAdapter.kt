package com.example.wanted_preonboarding_android.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.wanted_preonboarding_android.R

@BindingAdapter("bindImage")
fun ImageView.bindImage(imageUrl: String?) {
    imageUrl?.let {
        Glide.with(this).load(imageUrl).error(R.drawable.ic_no_image)
            .placeholder(R.drawable.ic_loading)
            .centerCrop().into(this)
    }
}