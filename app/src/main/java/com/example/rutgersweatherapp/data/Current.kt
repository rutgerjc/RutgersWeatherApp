package com.example.rutgersweatherapp.data


import com.squareup.moshi.Json

data class Current(
    val cloud: Int,
    val condition: Condition,
    val humidity: Int,
    val temp_f: Double,
    val wind_mph: Double
)