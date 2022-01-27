package com.example.taskandroid

import android.content.ContentResolver
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi

data class Image(val id: String, val uri: Uri) {
    fun toImageEntity() = ImageEntity(uri, id)

    fun toImageItem(contentResolver: ContentResolver?) = ImageItem(
        id = id,
        bitmap = MediaStore.Images.Media.getBitmap(
            contentResolver,
            uri
        )
    )
}