import com.example.weatherapp1.City
import com.example.weatherapp1.R


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp1.databinding.CityItemBinding
import com.example.weatherapp1.retrofit.CurrentWeather


class CityAdapter : RecyclerView.Adapter<CityAdapter.CityHolder>() {
    private val cityList = ArrayList<CurrentWeather>()

    class CityHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = CityItemBinding.bind(item)

        fun bind(weather: CurrentWeather) = with(binding) {
            textView2.text = weather.cityName
            textView3.text = "${weather.temperature}°C"
            textView4.text = weather.description
            Glide.with(im.context)
                .load(weather.imageUrl)
                .override(100, 100) // Resize the image
                .centerCrop() // Center crop the image
                .into(im)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.city_item, parent, false)
        return CityHolder(view)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.bind(cityList[position])
    }

    fun addCity(weather: CurrentWeather) {
        cityList.add(weather)
        notifyDataSetChanged()
    }
}
