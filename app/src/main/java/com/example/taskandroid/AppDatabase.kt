package com.example.taskandroid

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

const val TABLE_NAME = "PHOTOS_TABLE"

@Database(entities = [ImageEntity::class], version = 1)
@TypeConverters(ImageTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun citiesDao(): ImagesDao
}