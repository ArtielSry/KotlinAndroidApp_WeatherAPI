package com.art.weatherapi.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.art.weatherapi.R
import com.art.weatherapi.data.models.CurrentWeather

class WeatherAdapter(
    var WeatherList: List<CurrentWeather> = emptyList(),
) : RecyclerView.Adapter<WeatherViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_weather_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
       //holder.bind(WeatherList[position])
    }

    override fun getItemCount(): Int = WeatherList.size
}