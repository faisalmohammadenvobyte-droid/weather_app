package com.openkeyboard.myapplication.data.remote

import com.openkeyboard.myapplication.data.remote.dto.WeatherDto
import retrofit2.http.GET

interface ApiService {
    @GET
    suspend fun getWeatherData(): List<WeatherDto>
}