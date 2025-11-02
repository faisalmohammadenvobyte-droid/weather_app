package com.openkeyboard.myapplication.domain.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.openkeyboard.myapplication.domain.model.Weather

@Database([Weather::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}