package com.openkeyboard.myapplication.domain.model

data class ForecastEntry(
    // Time & Date
    val timestamp: Int,
    val dateTimeText: String,

    // Core Temperature
    val temperature: Double,
    val feelsLike: Double,

    // Condition
    val conditionMain: String,
    val conditionDescription: String,
    val iconCode: String,

    // Other Details
    val humidity: Int,
    val windSpeed: Double,
    val cloudiness: Int,
    val probabilityOfPrecipitation: Double
)

data class Forecast(
    val forecastEntries: List<ForecastEntry>
)
