package com.example.rutgersweatherapp

import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.rutgersweatherapp.BuildConfig.WEATHER_API_KEY
import com.example.rutgersweatherapp.api.RetrofitInstance
import com.example.rutgersweatherapp.api.WeatherApi
import com.example.rutgersweatherapp.data.CurrentWeatherResponse
import com.example.rutgersweatherapp.databinding.ActivityMainBinding
import com.example.rutgersweatherapp.repo.WeatherRepository
import com.example.rutgersweatherapp.viewmodel.WeatherViewModel
import com.example.rutgersweatherapp.viewmodel.WeatherViewModelFactory
import com.squareup.picasso.Picasso


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cityName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val weatherInstance = RetrofitInstance.getWeather()
        val repository = WeatherRepository(weatherInstance)
        val viewModel = ViewModelProvider(
            this,
            WeatherViewModelFactory(repository)
        )[WeatherViewModel::class.java]

        binding.IVSearch.setOnClickListener {
            cityName = binding.ETCityName.text.toString()
            if (cityName.isNotEmpty()) {
                viewModel.weatherCall("476efea3761b4cc1b15140912230108", cityName, 7)
                closeKeyBoard(binding.IVSearch)
            } else {
                Toast.makeText(this, "Input a city name", Toast.LENGTH_SHORT).show()
                closeKeyBoard(binding.IVSearch)
            }
        }

        viewModel.weather.observe(this) {
            binding.TVLocation.text = it.location.name
            binding.TVDate.text = it.location.localtime
            Picasso.get().load("http:" + it.current.condition.icon).resize(200, 200)
                .into(binding.IVWeatherCondition)
            binding.TVTemperature.text = it.current.temp_f.toString()
            binding.TVMaxTemp.text = it.forecast.forecastday[0].day.maxtemp_f.toString() + "° F"
            binding.TVMinTemp.text = it.forecast.forecastday[0].day.mintemp_f.toString() + "° F"
            binding.TVSunRise.text = it.forecast.forecastday[0].astro.sunrise
            binding.TVSunDownTime.text = it.forecast.forecastday[0].astro.sunset
            binding.TVWindSpeed.text = it.current.wind_mph.toString() + "mph"
            binding.TVCloud.text = it.current.cloud.toString() + " %"
            binding.TVRainChance.text =
                it.forecast.forecastday[0].day.daily_chance_of_rain.toString() + " %"
            binding.TVHumidity.text = it.forecast.forecastday[0].day.avghumidity.toString() + " %"
        }
    }
    private fun closeKeyBoard(view: View) {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}