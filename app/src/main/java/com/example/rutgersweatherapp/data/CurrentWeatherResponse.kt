package com.example.rutgersweatherapp.data


import com.squareup.moshi.Json

data class CurrentWeatherResponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)