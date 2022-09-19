package com.example.wanted_preonboarding_android.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Category(
    @StringRes var category: Int,
    @DrawableRes var image: Int
)
