package com.example.weatherapp1.retrofit

import com.example.weatherapp1.City
import retrofit2.http.GET

interface InterfaceCity {
    @GET("v1/current.json?q=Moscow")
   suspend fun getCity(): City
}