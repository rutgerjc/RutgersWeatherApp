package com.example.rutgersweatherapp.data

data class Forecastday(
    val astro: Astro,
    val date: String,
    val dateEpoch: Int,
    val day: Day,
)