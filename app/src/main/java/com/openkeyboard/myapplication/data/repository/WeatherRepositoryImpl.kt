package com.openkeyboard.myapplication.data.repository

import com.openkeyboard.myapplication.data.mapper.toDomain
import com.openkeyboard.myapplication.data.remote.ApiService
import com.openkeyboard.myapplication.domain.model.Weather
import com.openkeyboard.myapplication.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl(
    private val api: ApiService
): WeatherRepository {
    override suspend fun getWeatherData(lat: Double, lon: Double, apiKey: String): Weather = withContext(Dispatchers.IO) {
        api.getWeatherData(lat, lon, apiKey).toDomain()
    }
}