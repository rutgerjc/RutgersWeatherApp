package com.example.rutgersweatherapp.api

import androidx.lifecycle.MutableLiveData
import com.example.rutgersweatherapp.data.CurrentWeatherResponse
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

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