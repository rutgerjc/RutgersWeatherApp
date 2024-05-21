package com.example.rutgersweatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutgersweatherapp.repo.WeatherRepository
import com.example.rutgersweatherapp.data.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel (private val repository: WeatherRepository) : ViewModel() {

    val weather: MutableLiveData<CurrentWeatherResponse> = MutableLiveData()

    fun weatherCall(key: String, query: String, days: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCurrentWeather(key,query,days)
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    weather.value = response.body()
                } else {
                    throw IllegalArgumentException("Error")
                }
            }
        }
    }
}