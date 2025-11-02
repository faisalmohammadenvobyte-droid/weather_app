package com.openkeyboard.myapplication.data.remote

import com.openkeyboard.myapplication.data.remote.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String
    ): WeatherDto

}