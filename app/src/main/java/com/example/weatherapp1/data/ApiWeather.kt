package com.example.weatherapp1.data
import com.google.firebase.firestore.auth.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiWeather {
    interface ApiService {
        @GET("users/{user}")
        fun getUser(@Path("user") userId: String): Call<User>
    }
}