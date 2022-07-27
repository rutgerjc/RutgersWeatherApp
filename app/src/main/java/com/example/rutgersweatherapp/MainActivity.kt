package com.example.rutgersweatherapp

import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


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
    var PERMISSION_CODE = 1
    var locationManager: LocationManager? =null



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


    }


}