package com.example.weatherapp.ui.main

import android.content.Context
import com.example.weatherapp.domain.repository.ApiHelper
import com.example.weatherapp.ui.base.BaseViewModel
import android.location.Geocoder
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.local.database.SavedImagesDataBase
import com.example.weatherapp.data.model.ResponseModel
import com.example.weatherapp.data.model.SavedImageObject
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.util.*


class MainViewModel constructor(
    api: ApiHelper,
    dataBase: SavedImagesDataBase,
    var context: Context
) :
    BaseViewModel<ApiHelper>(api, dataBase, context) {

    var locationName = MutableLiveData<String>()
    var icon = MutableLiveData<String>()
    var desc = MutableLiveData<String>()
    var error = MutableLiveData<String>()
    var imageSaved = MutableLiveData<Boolean>()
    var responseModel = MutableLiveData<ResponseModel>()


    fun getAddress(lat: Double, lng: Double) {
        val geocoder = Geocoder(mContext, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(lat, lng, 1)
            val obj = addresses[0]
            var add = obj.getAddressLine(0)
            locationName.value = add
            Log.v("IGA", "Address$add")
        } catch (e: IOException) {

            Log.v("IGA", e.message!!)
        }
    }

    fun getCurrentWeatherData(lat: Double, lng: Double, part: String) {
        if (isNetworkConnected())
            apiHelper.getCurrentWeatherData(lat, lng, part)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<ResponseModel> {
                    override fun onSuccess(t: ResponseModel) {
                        responseModel.value = t
                        icon.value = t.current.weather[0].icon
                        desc.value = t.current.weather[0].description
                        getAddress(lat, lng)
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                        error.value = e.message
                    }

                })
        else
            error.value = "Check your internet connection"
    }

    fun save(imageDate: SavedImageObject) {
        Completable.fromAction { dataBase.daoAccess().insertOnlySingleOImage(imageDate) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onComplete() {
                    imageSaved.value = true
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    error.value = e.message!!
                }

            })
    }
}
