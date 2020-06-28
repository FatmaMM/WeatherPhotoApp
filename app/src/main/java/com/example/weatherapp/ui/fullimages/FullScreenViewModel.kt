package com.example.weatherapp.ui.fullimages

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.local.database.SavedImagesDataBase
import com.example.weatherapp.data.model.SavedImageObject
import com.example.weatherapp.domain.repository.ApiHelper
import com.example.weatherapp.ui.base.BaseViewModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FullScreenViewModel constructor(
    api: ApiHelper,
    dataBase: SavedImagesDataBase,
    conrext: Context
) : BaseViewModel<ApiHelper>(api, dataBase, conrext) {
}
