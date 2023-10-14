package com.art.weatherapi.data

import com.art.weatherapi.data.models.CurrentWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceWeather {
    @GET("weather?")
    suspend fun getCurrentWeather(
        @Query("q") city: String, @Query("units") units: String, @Query("appid") apiKey: String, @Query("lang") lang: String
    ): Response<CurrentWeather>
}