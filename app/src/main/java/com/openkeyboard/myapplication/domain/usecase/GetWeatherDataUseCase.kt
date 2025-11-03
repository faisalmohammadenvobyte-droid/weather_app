package com.openkeyboard.myapplication.domain.usecase

import com.openkeyboard.myapplication.domain.model.Forecast
import com.openkeyboard.myapplication.domain.model.Weather
import com.openkeyboard.myapplication.domain.repository.WeatherRepository

class GetWeatherDataUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(lat: Double, lon: Double, apiKey: String): Weather = weatherRepository.getWeatherData(lat, lon, apiKey)

    suspend operator fun invoke(city: String, apiKey: String): Forecast = weatherRepository.getForecast(city, apiKey)
}