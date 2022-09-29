package com.example.rutgersweatherapp.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    const val baseUrl ="https://api.weatherapi.com/v1/"

    fun getWeather(cityName: String): Retrofit {
            return Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

    }
}