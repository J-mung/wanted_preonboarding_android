package com.example.wanted_preonboarding_android.db

import androidx.room.TypeConverter
import com.example.wanted_preonboarding_android.models.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}