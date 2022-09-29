package com.example.rutgersweatherapp.data

data class Day(
    val condition: Condition,
    val daily_chance_of_rain: Int,
    val maxtemp_f: Double,
    val mintemp_f: Double,
    val avghumidity: Double
)