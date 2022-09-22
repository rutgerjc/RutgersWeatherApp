package com.example.rutgersweatherapp.repository

import com.example.rutgersweatherapp.api.WeatherApi

class WeatherRepository(private val instance: WeatherApi) {

    suspend fun getCurrentWeather(key: String, query: String, days: Int) = instance.getForecastWeather(key, query, days)
}