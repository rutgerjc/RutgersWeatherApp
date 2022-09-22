package com.example.rutgersweatherapp.data


import com.squareup.moshi.Json

data class Location(
    val country: String,
    val localtime: String,
    val name: String,
    val region: String,
)