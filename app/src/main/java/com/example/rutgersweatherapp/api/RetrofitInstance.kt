package com.example.rutgersweatherapp.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.intellij.lang.annotations.PrintFormat
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitInstance {

    //private const val baseUrl ="https://api.weatherapi.com/v1/"


    @Provides
    @Singleton
    fun getWeather(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.weatherapi.com/v1/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                //.create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun weathersService(retrofit: Retrofit): WeatherApi{
        return retrofit.create(WeatherApi::class.java)
    }
}