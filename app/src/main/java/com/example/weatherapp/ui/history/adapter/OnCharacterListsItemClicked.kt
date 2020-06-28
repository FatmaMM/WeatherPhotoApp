package com.example.weatherapp.ui.history.adapter

import com.example.weatherapp.data.model.SavedImageObject


interface OnCharacterListsItemClicked {
    fun onItemClick(item:SavedImageObject, pos: Int)
}