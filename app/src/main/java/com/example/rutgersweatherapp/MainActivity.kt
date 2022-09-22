package com.example.rutgersweatherapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.rutgersweatherapp.api.WeatherApi
import com.example.rutgersweatherapp.data.CurrentWeatherResponse
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var cityNameET: EditText
    lateinit var cityName: String
    lateinit var searchIV: ImageView
    lateinit var locationTV: TextView
    lateinit var dateTV: TextView
    lateinit var currentWeatherIV: ImageView
    lateinit var currentTempTV: TextView
    lateinit var minTempTV: TextView
    lateinit var maxTempTV: TextView
    lateinit var sunRiseTV: TextView
    lateinit var sundownTV: TextView
    lateinit var windSpeedTV: TextView
    lateinit var humidityTV: TextView
    lateinit var cloudyTV: TextView
    lateinit var rainyTV: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cityNameET = findViewById(R.id.ETCityName)
        searchIV = findViewById(R.id.IVSearch)
        locationTV = findViewById(R.id.TVLocation)
        dateTV = findViewById(R.id.TVDate)
        currentWeatherIV = findViewById(R.id.IVWeatherCondition)
        currentTempTV = findViewById(R.id.TVTemperature)
        minTempTV = findViewById(R.id.TVMinTemp)
        maxTempTV = findViewById(R.id.TVMaxTemp)
        sunRiseTV = findViewById(R.id.TVSunRise)
        sundownTV = findViewById(R.id.TVSunDownTime)
        windSpeedTV = findViewById(R.id.TVWindSpeed)
        cloudyTV = findViewById(R.id.TVCloud)
        humidityTV  = findViewById(R.id.TVHumidity)
        rainyTV = findViewById(R.id.TVRainChance)



        searchIV.setOnClickListener{
            cityName = cityNameET.text.toString()
            if (cityName.isNotEmpty()){
                getWeatherInfo(cityName)
            }
            closeKeyBoard(searchIV)
        }
        cityNameET.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode == KEYCODE_ENTER) {
                cityName = cityNameET.text.toString()
                getWeatherInfo(cityName)
                closeKeyBoard(cityNameET)
                true
            } else {
                false
            }
        }
    }

    @SuppressLint("SetTextI18n")
    @OptIn(DelicateCoroutinesApi::class)
    private fun getWeatherInfo(cityName: String){
        val api = Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<CurrentWeatherResponse> =
                api.getForecastWeather(BuildConfig.WEATHER_API_KEY, cityName, 7).awaitResponse()
            if (response.isSuccessful) {
                val data = response.body()!!

                withContext(Dispatchers.Main) {
                    locationTV.text = data.location.name
                    dateTV.text = data.location.localtime
                    Picasso.get().load("http:" + data.current.condition.icon).into(currentWeatherIV)
                    currentTempTV.text = data.current.temp_f.toString() + "° F"
                    maxTempTV.text = data.forecast.forecastday[0].day.maxtemp_f.toString() + "° F"
                    minTempTV.text = data.forecast.forecastday[0].day.mintemp_f.toString() + "° F"
                    sunRiseTV.text = data.forecast.forecastday[0].astro.sunrise
                    sundownTV.text = data.forecast.forecastday[0].astro.sunset
                    windSpeedTV.text = data.current.wind_mph.toString() + "mph"
                    cloudyTV.text = data.current.cloud.toString() + "%"
                    rainyTV.text =
                        data.forecast.forecastday[0].day.daily_chance_of_rain.toString() + "%"
                    humidityTV.text = data.forecast.forecastday[0].day.avghumidity.toString() + "%"

                }
            }
        }

    }

    private fun closeKeyBoard(view: View) {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}