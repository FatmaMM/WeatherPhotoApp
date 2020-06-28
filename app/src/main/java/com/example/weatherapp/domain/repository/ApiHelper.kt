package com.example.weatherapp.domain.repository

import com.example.weatherapp.data.model.ResponseModel
import io.reactivex.Single


interface ApiHelper {
    fun getCurrentWeatherData(
        lat: Double,
        lng: Double,
        part: String
    ): Single<ResponseModel>
}