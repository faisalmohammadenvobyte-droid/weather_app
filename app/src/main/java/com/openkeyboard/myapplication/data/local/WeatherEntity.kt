package com.openkeyboard.myapplication.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
data class WeatherEntity(
    @PrimaryKey val city: String,
    val country: String,
    val temperature: Double,
    val tempMin: Double,
    val tempMax: Double,
    val conditionMain: String,
    val conditionDescription: String,
    val iconCode: String,
    val humidity: Int,
    val windSpeed: Double,
    val sunriseTimestamp: Int,
    val sunsetTimestamp: Int
)