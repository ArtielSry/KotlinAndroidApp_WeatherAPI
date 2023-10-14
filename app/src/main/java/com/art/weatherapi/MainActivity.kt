package com.art.weatherapi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.art.weatherapi.databinding.ActivityMainBinding
import com.art.weatherapi.utils.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
                RetrofitInstance.api.getCurrentWeather("Berlin", "metric", KEY_API, "en")
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
                    binding.tvSunrise.text = "${response.body()!!.main.feels_like.toInt()}º"

                    fun date(): String {
                        val locale = Locale("en", "US")
                        val sdf = SimpleDateFormat("dd MMMM yyyy", locale)
                        return sdf.format((Date()))
                    }

                    fun dayName(timestamp: Long): String {
                        val locale = Locale("en", "US")
                        val sdf = SimpleDateFormat("EEEE", locale)
                        return sdf.format((Date()))
                    }

                    binding.tvDate.text = date()
                    binding.tvDay.text = dayName(System.currentTimeMillis())


                    binding.weatherMapWebView.settings.javaScriptEnabled = true
                    val mapUrl = "https://openweathermap.org/weathermap?basemap=map&cities=false&layer=precipitation&lat=52.5244&lon=13.4105&zoom=5&apiKey=$KEY_API"



                    binding.weatherMapWebView.loadUrl(mapUrl)
                }
            }
        }


        fun dayName(timestamp: Long): String {
            val sdf = SimpleDateFormat("EEEE", Locale.getDefault())
            return sdf.format((Date()))
        }
    }
}
