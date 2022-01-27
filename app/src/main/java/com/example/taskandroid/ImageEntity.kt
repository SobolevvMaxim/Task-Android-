package com.example.taskandroid

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TABLE_NAME)
data class ImageEntity(
    @ColumnInfo(name = "uri") val uri: Uri,
    @PrimaryKey val id: String
) {
    fun toImage() = Image(id, uri)
}