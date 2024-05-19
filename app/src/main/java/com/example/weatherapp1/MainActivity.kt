package com.example.weatherapp1

import android.annotation.SuppressLint
import android.app.PendingIntent.getActivity
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp1.databinding.ActivityMainBinding
import com.example.weatherapp1.retrofit.InterfaceCity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = CityAdapter()
    val tv = findViewById<TextView>(R.id.tv)


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com")
            .addConverterFactory(GsonConverterFactory.create()).build()
            val InterfaceCity = retrofit.create(InterfaceCity::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val city = InterfaceCity.getCity()
            tv.text = city.name
        }
        init()




    }

    private fun init() = with(binding) {
        binding.apply {
            RcWeather.layoutManager = GridLayoutManager(this@MainActivity, 1)
            RcWeather.adapter = adapter
            bt.setOnClickListener {
                adapter.addCity(city = City("Moscow","1","1",2.2,1.2,"asd",2,"a"))

            }
        }
    }

}


