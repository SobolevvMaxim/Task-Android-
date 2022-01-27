package com.example.taskandroid

import android.net.Uri
import androidx.room.TypeConverter

class UriConverter {

    @TypeConverter
    fun fromUri(uri: Uri): String = uri.toString()

    @TypeConverter
    fun toUri(uriString: String) = Uri.parse(uriString)
}