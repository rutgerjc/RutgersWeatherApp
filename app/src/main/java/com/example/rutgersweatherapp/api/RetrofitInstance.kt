package com.example.rutgersweatherapp.api

import androidx.lifecycle.MutableLiveData
import com.example.rutgersweatherapp.data.CurrentWeatherResponse
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {
    var weather: MutableLiveData<CurrentWeatherResponse> = MutableLiveData()

    fun getWeather(cityName: String): WeatherApi {

//        GlobalScope.launch(Dispatchers.IO) {
//            val response: Response<CurrentWeatherResponse> =
//                retrofit.getForecastWeather("3ae43ff4179b4c88890134628222905", cityName, 1)
//                    .awaitResponse()
//            if (response.isSuccessful) {
//                weather.value = response.body()!!
//            }
//        }
        return Retrofit
            .Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}