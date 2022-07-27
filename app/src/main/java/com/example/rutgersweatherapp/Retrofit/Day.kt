package com.example.rutgersweatherapp.Retrofit

data class Day(
    val condition: Condition,
    val daily_chance_of_rain: Int,
    val daily_will_it_rain: Int,
    val maxtemp_f: Double,
    val maxwind_mph: Double,
    val mintemp_f: Double
)