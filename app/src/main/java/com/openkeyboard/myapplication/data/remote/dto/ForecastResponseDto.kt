package com.openkeyboard.myapplication.data.remote.dto

data class ForecastResponseDto(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForecastItemDto>,
    val message: Int
)