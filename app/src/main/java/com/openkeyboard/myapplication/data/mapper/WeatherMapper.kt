package com.openkeyboard.myapplication.data.mapper

import com.openkeyboard.myapplication.data.remote.dto.WeatherDto
import com.openkeyboard.myapplication.domain.model.Weather

fun WeatherDto.toDomain() = Weather(
    city = name,
    country = sys.country,
    temperature = main.temp,
    tempMin = main.tempMin,
    tempMax = main.tempMax,
    conditionMain = weather.firstOrNull()?.main ?: "Unknown",
    conditionDescription = weather.firstOrNull()?.description ?: "N/A",
    iconCode = weather.firstOrNull()?.icon ?: "",
    humidity = main.humidity,
    windSpeed = wind.speed,
    sunriseTimestamp = sys.sunrise,
    sunsetTimestamp = sys.sunset
)