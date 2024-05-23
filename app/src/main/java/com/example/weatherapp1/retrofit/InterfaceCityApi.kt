package com.example.weatherapp1.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

    interface InterfaceCityApi {
        @GET("current.json")
        suspend fun getCity(
            @Query("key") apiKey: String,
            @Query("q") cityName: String
        ): WeatherResponse
    }

    data class WeatherResponse(
        val location: Location,
        val current: Current
    )

    data class Location(
        val name: String
    )

    data class Current(
        val temp_c: Double,
        val condition: Condition
    )

    data class Condition(
        val text: String,
        val icon: String

    )
