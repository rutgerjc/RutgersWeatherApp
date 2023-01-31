package com.example.rutgersweatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutgersweatherapp.BuildConfig
import com.example.rutgersweatherapp.api.WeatherApi
import com.example.rutgersweatherapp.data.CurrentWeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherApi: WeatherApi) : ViewModel() {

    private val _responseLiveData = MutableLiveData<CurrentWeatherResponse>()
    val responseLiveData = _responseLiveData

    suspend fun weatherCall(cityName: String, days: Int) {
        viewModelScope.launch {
            val response = weatherApi.getForecastWeather(BuildConfig.WEATHER_API_KEY, cityName, days)
            _responseLiveData.value = response.body()!!
        }
    }
}