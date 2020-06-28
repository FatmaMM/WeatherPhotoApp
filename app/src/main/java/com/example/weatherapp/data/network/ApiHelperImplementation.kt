package com.example.weatherapp.data.network

import com.example.weatherapp.data.model.ResponseModel
import com.example.weatherapp.data.network.ApiEndPoint.APPKEY
import com.example.weatherapp.data.network.ApiEndPoint.BaseUrl
import com.example.weatherapp.domain.repository.ApiHelper
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ApiHelperImplementation @Inject constructor() : ApiHelper {


    override fun getCurrentWeatherData(
        lat: Double,
        lng: Double,
        part: String
    ): Single<ResponseModel> {
        return Rx2AndroidNetworking.get(BaseUrl)
            .addQueryParameter("lat", lat.toString())
            .addQueryParameter("lon", lng.toString())
            .addQueryParameter("units", "metric")
            .addQueryParameter("appid", APPKEY)
            .build()
            .getObjectSingle(ResponseModel::class.java)
    }


}