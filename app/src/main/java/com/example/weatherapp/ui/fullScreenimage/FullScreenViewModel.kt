package com.example.weatherapp.ui.fullScreenimage

import android.content.Context
import com.example.weatherapp.data.local.database.SavedImagesDataBase
import com.example.weatherapp.domain.repository.ApiHelper
import com.example.weatherapp.ui.base.BaseViewModel

class FullScreenViewModel constructor(
    api: ApiHelper,
    dataBase: SavedImagesDataBase,
    conrext: Context
) : BaseViewModel<ApiHelper>(api, dataBase, conrext) {
}
