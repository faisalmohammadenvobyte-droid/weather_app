package com.openkeyboard.myapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    // Observe weather data reactively (for Compose)
    @Query("SELECT * FROM weather_table WHERE city = :city LIMIT 1")
    fun getWeatherFlow(city: String): Flow<WeatherEntity?>

    // For one-time fetch
    @Query("SELECT * FROM weather_table WHERE city = :city LIMIT 1")
    suspend fun getWeather(city: String): WeatherEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: WeatherEntity)
}