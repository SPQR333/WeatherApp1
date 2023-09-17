package com.example.weatherapp1

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp1.databinding.ActivityMainBinding
import org.json.JSONObject

const val API_KEY = "d8906e5262d844788de163943231309"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
    private fun getResult(name:String) {
        val url = "https://api.weatherapi.com/v1/current.json?" +
                "key=$API_KEY&q=$name&aqi=no"

        val  queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET,
            url,
            {
                    response->
                val obj = JSONObject(response)
                val temp = obj.getJSONObject("current")
                Log.d("MyLog","Volley error: ${temp.getString("temp_C")}")

                Log.d("MyLog","Volley error: $response")


            },
            {
                Log.d("MyLog","Volley error: $it")
            }
        )
        queue.add(stringRequest)

    }
}