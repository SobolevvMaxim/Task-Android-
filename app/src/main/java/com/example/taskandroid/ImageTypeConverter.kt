package com.example.taskandroid

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream
import java.util.*

class ImageTypeConverter {

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromBitmap(bitmap: Bitmap): String {
        val array = ByteArrayOutputStream().apply {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, this)
        }.toByteArray()
        return Base64.getEncoder().encodeToString(array)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toUri(byteArray: String): Bitmap {
        val bytes = Base64.getDecoder().decode(
            byteArray.substring(byteArray.indexOf(",") + 1)
        )
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }
}