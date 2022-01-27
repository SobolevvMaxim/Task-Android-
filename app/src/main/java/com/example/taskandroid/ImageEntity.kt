package com.example.taskandroid

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TABLE_NAME)
data class ImageEntity(
    @ColumnInfo(name = "bitmap") val bitmap: Bitmap,
    @PrimaryKey val id: String
) {
    fun toImage() = Image(bitmap)
}