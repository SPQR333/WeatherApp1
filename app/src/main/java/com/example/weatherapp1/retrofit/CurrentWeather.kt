package com.example.weatherapp1.retrofit

data class CurrentWeather(
    val cityName: String,
    val temperature: Double,
    val description: String,
    val imageUrl: String
)