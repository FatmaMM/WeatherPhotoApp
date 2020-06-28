package com.example.weatherapp.data.model

class ResponseModel {
    lateinit var current: Current

    class Current {
        lateinit var temp: String
        lateinit var weather: ArrayList<Weather>
    }
}

class Weather {
    var id: Int = 0
    lateinit var main: String
    lateinit var description: String
    lateinit var icon: String
}