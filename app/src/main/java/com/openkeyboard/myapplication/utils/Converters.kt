package com.openkeyboard.myapplication.utils

class Converters {
    companion object {
        fun kelvinToCelsius(kelvin: Double): Int {
            return (kelvin - 273.15).toInt()
        }
    }
}