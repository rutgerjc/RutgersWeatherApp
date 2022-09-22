package com.example.rutgersweatherapp.data


import com.squareup.moshi.Json

data class Forecast(
    val forecastday: List<Forecastday>
)