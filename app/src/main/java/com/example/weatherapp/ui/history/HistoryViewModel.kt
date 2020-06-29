package com.example.weatherapp.ui.history

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

class HistoryViewModel constructor(
    api: ApiHelper,
    dataBase: SavedImagesDataBase,
    context: Context
) : BaseViewModel<ApiHelper>(api, dataBase, context) {
    init {
        getImages()
    }
    var error = MutableLiveData<String>()
    var images = MutableLiveData<ArrayList<SavedImageObject>>()

    private fun getImages() {
        dataBase.daoAccess().fetchSavedImages()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<SavedImageObject>> {
                override fun onSuccess(t: List<SavedImageObject>) {
                    images.value = t as ArrayList<SavedImageObject>
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    error.value = e.message
                }
            })

    }
}
