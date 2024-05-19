package com.example.weatherapp1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp1.databinding.CityItemBinding

class CityAdapter : RecyclerView.Adapter<CityAdapter.CityHolder>() {
    val cityList = ArrayList<City>()
    class CityHolder(item : View) : RecyclerView.ViewHolder(item) {
        val binding = CityItemBinding.bind(item)
        fun bind(city: City) = with(binding) {

            tvTitle.text = city.name

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.city_item,parent,false)
        return CityHolder(view)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.bind(cityList[position])
    }

    fun addCity(city: City) {
        cityList.add(city)
        notifyDataSetChanged()
    }
}