package com.example.rutgersweatherapp.data


import com.squareup.moshi.Json

data class Forecastday(
    val astro: Astro,
    val date: String,
    val dateEpoch: Int,
    val day: Day,
)