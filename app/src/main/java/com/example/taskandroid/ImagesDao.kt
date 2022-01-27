package com.example.taskandroid

import android.graphics.Bitmap
import android.net.Uri
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ImagesDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): List<ImageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: ImageEntity)

    @Query("DELETE FROM $TABLE_NAME WHERE bitmap = :bitmapOfImageToDelete")
    fun delete(bitmapOfImageToDelete: Bitmap)
}