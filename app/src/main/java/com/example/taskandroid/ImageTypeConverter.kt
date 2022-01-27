package com.example.taskandroid

import android.net.Uri
import androidx.room.TypeConverter

class ImageTypeConverter {

    @TypeConverter
    fun fromUri(uri: Uri): String = uri.toString()

    @TypeConverter
    fun toUri(uri: String): Uri = Uri.parse(uri)
}