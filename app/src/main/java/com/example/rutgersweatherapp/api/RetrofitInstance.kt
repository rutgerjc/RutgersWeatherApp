package com.example.rutgersweatherapp.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object RetrofitInstance {

    fun getWeather(): WeatherApi {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.weatherapi.com/v1/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                return retrofit.create(WeatherApi::class.java)
    }
}