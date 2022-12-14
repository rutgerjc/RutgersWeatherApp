package com.example.rutgersweatherapp.api

import com.example.rutgersweatherapp.data.CurrentWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast.json")
     suspend fun getForecastWeather(
        @Query("key") apikey: String,
        @Query("q") location: String,
        @Query("days") days: Int
    ): Response<CurrentWeatherResponse>
}