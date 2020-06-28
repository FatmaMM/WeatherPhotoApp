package com.example.weatherapp.data.local.database

import androidx.room.*
import com.example.weatherapp.data.model.SavedImageObject
import io.reactivex.Single

@Dao
interface DaoAccess {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOnlySingleOImage(imageObject: SavedImageObject)

    @Query("SELECT * FROM `SavedImage`")
    fun fetchSavedImages(): Single<List<SavedImageObject>>

    @Update
    fun updateImage(order: SavedImageObject)

    @Delete
    fun deleteImage(order: SavedImageObject)

    @Query("DELETE FROM `SavedImage`")
    fun deleteAll()
}