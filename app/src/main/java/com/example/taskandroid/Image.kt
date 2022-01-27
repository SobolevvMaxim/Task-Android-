package com.example.taskandroid

import android.graphics.Bitmap

data class Image(val bitmap: Bitmap) {
    fun toImageEntity(id: String) = ImageEntity(bitmap, id)
}