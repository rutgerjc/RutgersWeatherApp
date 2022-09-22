package com.example.rutgersweatherapp.data


import com.squareup.moshi.Json

data class Condition(
    val code: Int,
    val icon: String,
    val text: String
)