package com.openkeyboard.myapplication.domain.model

data class Weather(
    // Location Details
    val city: String,
    val country: String,

    // Core Temperature
    val temperature: Double,
    val tempMin: Double,
    val tempMax: Double,

    // Conditions
    val conditionMain: String,     // e.g., "Clouds"
    val conditionDescription: String, // e.g., "broken clouds"
    val iconCode: String,          // e.g., "04d"

    // Other Details
    val humidity: Int,
    val windSpeed: Double,
    val sunriseTimestamp: Int,
    val sunsetTimestamp: Int
)
