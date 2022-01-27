package com.example.taskandroid

import android.app.Application
import androidx.room.Room

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        appDatabase = Room.databaseBuilder(this, AppDatabase::class.java, APP_DATABASE).build()
    }

    companion object {
        private const val APP_DATABASE = "APP_DATABASE"

        private lateinit var appDatabase: AppDatabase

        fun getImagesDao() = appDatabase.citiesDao()
    }
}