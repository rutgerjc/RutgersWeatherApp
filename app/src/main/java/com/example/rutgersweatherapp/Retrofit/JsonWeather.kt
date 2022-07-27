package com.example.rutgersweatherapp.Retrofit

data class JsonWeather(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)