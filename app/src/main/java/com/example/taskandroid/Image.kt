package com.example.taskandroid

import android.net.Uri

data class Image(val uri: Uri) {
    fun toImageEntity(id: String) = ImageEntity(uri, id)
}