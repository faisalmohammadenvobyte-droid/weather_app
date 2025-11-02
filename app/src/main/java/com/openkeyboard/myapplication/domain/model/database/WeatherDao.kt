package com.openkeyboard.myapplication.domain.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.openkeyboard.myapplication.domain.model.Weather

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather_table WHERE city = :city LIMIT 1")
    suspend fun getWeatherByCity(city: String): Weather?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: Weather)

    @Query("DELETE FROM weather_table WHERE city = :city")
    suspend fun deleteByCity(city: String)

    @Query("DELETE FROM weather_table")
    suspend fun clearAll()
}