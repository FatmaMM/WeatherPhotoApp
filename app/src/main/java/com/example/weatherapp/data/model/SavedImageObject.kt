package com.example.weatherapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "SavedImage")
class SavedImageObject {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "id")
    var custom_id: Int = 0
    @ColumnInfo(name = "imagepath")
    var imagepath: String? = ""
    @ColumnInfo(name = "icon")
    var icon: String? = ""
    @ColumnInfo(name = "desc")
    var desc: String? = ""
    @ColumnInfo(name = "address")
    var address: String? = ""
    @ColumnInfo(name = "temp")
    var temp: String? = ""
}