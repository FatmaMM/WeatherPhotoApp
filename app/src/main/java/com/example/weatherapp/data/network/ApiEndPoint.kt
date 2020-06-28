package com.example.weatherapp.data.network


object ApiEndPoint {
    val BaseUrl =
        "https://api.openweathermap.org/data/2.5/onecall"
    val APPKEY="264185bd6e59180905b465e14d2e5385"
//    val iconURl="http://openweathermap.org/img/wn/10d@2x.png"
    val iconURl="http://openweathermap.org/img/wn"
//    https://api.openweathermap.org/data/2.5/onecall?lat={lat}&lon={lon}&
//exclude={part}&appid={YOUR API KEY}
//    https://api.openweathermap.org/data/2.5/onecall?lat=33.441792&lon=-94.037689&
//exclude=hourly,daily&appid={YOUR API KEY}

}
/* Getting current, minute, hourly and daily forecast weather data

https://api.openweathermap.org/data/2.5/onecall?lat={lat}&lon={lon}&appid={YOUR API KEY}

Getting historical weather data for 5 previous days

https://api.openweathermap.org/data/2.5/onecall/timemachine?lat={lat}&lon={lon}&dt={time}&appid={YOUR API KEY}
264185bd6e59180905b465e14d2e5385
{
    "lat": 33.44,
    "lon": -94.04,
    "timezone": "America/Chicago",
    "timezone_offset": -18000,
    "current": {
        "dt": 1588935779,
        "sunrise": 1588936856,
        "sunset": 1588986260,
        "temp": 16.75,
        "feels_like": 16.07,
        "pressure": 1009,
        "humidity": 93,
        "dew_point": 15.61,
        "uvi": 8.97,
        "clouds": 90,
        "visibility": 12874,
        "wind_speed": 3.6,
        "wind_deg": 280,
        "weather": [
        {
            "id": 501,
            "main": "Rain",
            "description": "moderate rain",
            "icon": "10n"
        },
        {
            "id": 200,
            "main": "Thunderstorm",
            "description": "thunderstorm with light rain",
            "icon": "11n"
        }
        ],
        "rain": {
        "1h": 2.79
    }
    },
    "minutely": [
    {
        "dt": 1588935780,
        "precipitation": 2.789
    },
    ...
},
"hourly": [
{
    "dt": 1588935600,
    "temp": 16.75,
    "feels_like": 13.93,
    "pressure": 1009,
    "humidity": 93,
    "dew_point": 15.61,
    "clouds": 90,
    "wind_speed": 6.66,
    "wind_deg": 203,
    "weather": [
    {
        "id": 501,
        "main": "Rain",
        "description": "moderate rain",
        "icon": "10n"
    }
    ],
    "rain": {
    "1h": 2.92
}
},
...
}
"daily": [
{
    "dt": 1588960800,
    "sunrise": 1588936856,
    "sunset": 1588986260,
    "temp": {
    "day": 22.49,
    "min": 10.96,
    "max": 22.49,
    "night": 10.96,
    "eve": 18.45,
    "morn": 18.14
},
    "feels_like": {
    "day": 18.72,
    "night": 6.53,
    "eve": 16.34,
    "morn": 16.82
},
    "pressure": 1014,
    "humidity": 60,
    "dew_point": 14.35,
    "wind_speed": 7.36,
    "wind_deg": 342,
    "weather": [
    {
        "id": 502,
        "main": "Rain",
        "description": "heavy intensity rain",
        "icon": "10d"
    }
    ],
    "clouds": 68,
    "rain": 15.38,
    "uvi": 8.97
},
...
}*/