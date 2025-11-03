package com.openkeyboard.myapplication.data.mapper

import com.openkeyboard.myapplication.data.remote.dto.ForecastItemDto
import com.openkeyboard.myapplication.data.remote.dto.ForecastResponseDto
import com.openkeyboard.myapplication.domain.model.Forecast
import com.openkeyboard.myapplication.domain.model.ForecastEntry

fun ForecastItemDto.toDomain(): ForecastEntry {
    val condition = weather.firstOrNull()
    return ForecastEntry(
        timestamp = dt,
        dateTimeText = dt_txt,
        temperature = main.temp,
        feelsLike = main.feels_like,
        conditionMain = condition?.main ?: "Unknown",
        conditionDescription = condition?.description ?: "N/A",
        iconCode = condition?.icon.orEmpty(),
        humidity = main.humidity,
        windSpeed = wind.speed,
        cloudiness = clouds.all,
        probabilityOfPrecipitation = pop
    )
}

fun ForecastResponseDto.toDomain(): Forecast {
    return Forecast(
        forecastEntries = list.map { it.toDomain() }
    )
}
