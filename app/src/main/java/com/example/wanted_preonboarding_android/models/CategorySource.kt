package com.example.wanted_preonboarding_android.models

import com.example.wanted_preonboarding_android.R

class CategorySource() {
    fun loadCategories(): List<Category> {
        return listOf<Category>(
            Category(R.string.category_1, R.drawable.ic_category_1),
            Category(R.string.category_2, R.drawable.ic_category_2),
            Category(R.string.category_3, R.drawable.ic_category_3),
            Category(R.string.category_4, R.drawable.ic_category_4),
            Category(R.string.category_5, R.drawable.ic_category_5),
            Category(R.string.category_6, R.drawable.ic_category_6),
            Category(R.string.category_7, R.drawable.ic_category_7),
        )
    }
}