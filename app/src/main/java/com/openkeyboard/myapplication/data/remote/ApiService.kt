package com.openkeyboard.myapplication.data.remote

import com.openkeyboard.myapplication.data.remote.dto.ForecastResponseDto
import com.openkeyboard.myapplication.data.remote.dto.WeatherResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String
    ): WeatherResponseDto

    @GET("forecast")
    suspend fun getForecasts(
        @Query("q") city: String,
        @Query("appid") apiKey: String
    ): ForecastResponseDto
}