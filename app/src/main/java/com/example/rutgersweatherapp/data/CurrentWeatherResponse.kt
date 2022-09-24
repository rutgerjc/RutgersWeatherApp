package com.example.rutgersweatherapp.data

data class CurrentWeatherResponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)