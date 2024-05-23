package com.example.weatherapp1

import CityAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp1.databinding.ActivityMainBinding
import com.example.weatherapp1.retrofit.CurrentWeather
import com.example.weatherapp1.retrofit.InterfaceCityApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var weatherAdapter: CityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Инициализация RecyclerView
        binding.RcWeather.layoutManager = LinearLayoutManager(this)
        weatherAdapter = CityAdapter()
        binding.RcWeather.adapter = weatherAdapter

        // Пример загрузки данных о погоде при нажатии на кнопку
        binding.bt.setOnClickListener {
            val cityName = binding.etCityName.text.toString().trim()
            if (cityName.isNotEmpty()) {
                loadWeatherData(cityName)
            }
        }
    }

    private fun loadWeatherData(cityName: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val interfaceCityApi = retrofit.create(InterfaceCityApi::class.java)

        // Загрузка данных о погоде
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val cityWeather = interfaceCityApi.getCity("69aa971b26a64d5e828152739241805", cityName)
                val currentWeather = CurrentWeather(
                    cityWeather.location.name,
                    cityWeather.current.temp_c,
                    cityWeather.current.condition.text,
                    "https:" + cityWeather.current.condition.icon // Добавление URL изображения

                )
                withContext(Dispatchers.Main) {
                    weatherAdapter.addCity(currentWeather)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
