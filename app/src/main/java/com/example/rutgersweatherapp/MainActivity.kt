package com.example.rutgersweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.rutgersweatherapp.BuildConfig.WEATHER_API_KEY
import com.example.rutgersweatherapp.api.WeatherApi
import com.example.rutgersweatherapp.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cityName: String

    @Inject
    lateinit var weatherApi: WeatherApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

             suspend fun weatherNetworkCall(cityName: String) {
                 lifecycleScope.launchWhenStarted {
                     val response = weatherApi.getForecastWeather(WEATHER_API_KEY, cityName, 7)
                     Log.i("DATA", response.body()!!.toString())
                     val data = response.body()!!
                     withContext(Dispatchers.Main){
                         binding.TVLocation.text = data.location.name
                         binding.TVDate.text = data.location.localtime
                         Picasso.get().load("http:" + data.current.condition.icon)
                             .into(binding.IVWeatherCondition)
                         binding.TVTemperature.text = data.current.temp_f.toString()
                         binding.TVMaxTemp.text = data.forecast.forecastday[0].day.maxtemp_f.toString() + "° F"
                         binding.TVMinTemp.text = data.forecast.forecastday[0].day.mintemp_f.toString() + "° F"
                         binding.TVSunRise.text = data.forecast.forecastday[0].astro.sunrise
                         binding.TVSunDownTime.text = data.forecast.forecastday[0].astro.sunset
                         binding.TVWindSpeed.text = data.current.wind_mph.toString() + "mph"
                         binding.TVCloud.text = data.current.cloud.toString() + " %"
                         binding.TVRainChance.text = data.forecast.forecastday[0].day.daily_chance_of_rain.toString() + " %"
                         binding.TVHumidity.text = data.forecast.forecastday[0].day.avghumidity.toString() + " %"
                     }
                 }
            }
        binding.IVSearch.setOnClickListener{
            cityName = binding.ETCityName.text.toString()
            if (cityName.isNotEmpty()) {
                GlobalScope.launch {
                    weatherNetworkCall(cityName)
                    closeKeyBoard(binding.IVSearch)
                }
            } else{
                Toast.makeText(this, "Input a city name", Toast.LENGTH_SHORT).show()
                closeKeyBoard(binding.IVSearch)
            }
        }
        binding.ETCityName.setOnEditorActionListener { _, actionId, event ->
            cityName = binding.ETCityName.text.toString()
            if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode == KEYCODE_ENTER && cityName.isNotEmpty()) {
                GlobalScope.launch {
                    weatherNetworkCall(cityName)
                    closeKeyBoard(binding.IVSearch)
                }
                true
            } else {
                Toast.makeText(this, "Input a city name", Toast.LENGTH_SHORT).show()
                closeKeyBoard(binding.ETCityName)
                false
            }
        }
    }
    private fun closeKeyBoard(view: View) {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}