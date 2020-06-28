package com.example.weatherapp.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "SavedImage")
class SavedImageObject() : Parcelable {
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

    constructor(parcel: Parcel) : this() {
        custom_id = parcel.readInt()
        imagepath = parcel.readString()
        icon = parcel.readString()
        desc = parcel.readString()
        address = parcel.readString()
        temp = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(custom_id)
        parcel.writeString(imagepath)
        parcel.writeString(icon)
        parcel.writeString(desc)
        parcel.writeString(address)
        parcel.writeString(temp)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SavedImageObject> {
        override fun createFromParcel(parcel: Parcel): SavedImageObject {
            return SavedImageObject(parcel)
        }

        override fun newArray(size: Int): Array<SavedImageObject?> {
            return arrayOfNulls(size)
        }
    }
}