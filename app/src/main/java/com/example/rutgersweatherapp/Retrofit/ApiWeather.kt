package com.example.rutgersweatherapp.Retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "3ae43ff4179b4c88890134628222905"

//http://api.weatherapi.com/v1/forecast.json?key=3ae43ff4179b4c88890134628222905&q=London&days=1&aqi=yes&alerts=no

interface ApiWeather {

    //@GET("/v1/forecast.json?key=3ae43ff4179b4c88890134628222905&q=${cityName}&days=1&aqi=yes&alerts=no")

    fun getWeather(
        @Query("q") location: String
    ): Call<Location>
}