package com.art.weatherapi

import android.annotation.SuppressLint
import android.net.http.HttpException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import com.art.weatherapi.data.models.Weather
import com.art.weatherapi.databinding.ActivityMainBinding
import com.art.weatherapi.utils.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.http.Query
import java.io.IOException

class MainActivity : AppCompatActivity() {

    companion object {
        const val KEY_API = "a76bf026144b68ef567ca88a8946bcf2"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        getCurrentWeather()
    }



    @SuppressLint("SetTextI18n")
    private fun getCurrentWeather() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.api.getCurrentWeather("madrid", "metric", KEY_API)
            } catch (e: IOException) {
                Toast.makeText(applicationContext, "app error ${e.message}", Toast.LENGTH_SHORT)
                    .show()
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    binding.tvDegrees.text = "${response.body()!!.main.temp.toInt()}º"
                    binding.tvCity.text = response.body()!!.name
                    binding.tvCountry.text = ", ${response.body()!!.sys.country}"


                    binding.tvWeather.text = response.body()!!.weather.firstOrNull()?.main

                    binding.tvWind.text = "${response.body()!!.wind.speed}km"
                    binding.tvHumidity.text = "${response.body()!!.main.humidity}%"
                    binding.tvSunrise.text = "${response.body()!!.main.temp_min}º"
                }
            }
        }
    }
}
