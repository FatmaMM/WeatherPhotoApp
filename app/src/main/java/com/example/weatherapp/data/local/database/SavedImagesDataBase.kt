package com.example.weatherapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.data.model.SavedImageObject

@Database(entities = arrayOf(SavedImageObject::class), version = 1)
abstract class SavedImagesDataBase: RoomDatabase() {
    abstract fun daoAccess(): DaoAccess
}