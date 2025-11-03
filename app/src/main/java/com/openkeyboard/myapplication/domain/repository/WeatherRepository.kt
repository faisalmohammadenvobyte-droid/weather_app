package com.openkeyboard.myapplication.domain.repository

import com.openkeyboard.myapplication.domain.model.Forecast
import com.openkeyboard.myapplication.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, lon: Double, apiKey: String): Weather

    suspend fun getForecast(city: String, apiKey: String): Forecast
}