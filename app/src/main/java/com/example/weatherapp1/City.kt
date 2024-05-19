package com.example.weatherapp1

data class City(val name: String,
                val region: String,
                val country: String,
                val lat: Double,
                val lon: Double,
                val tz_id: String,
                val localtime_epoch: Int,
                val localtime: String)