package com.openkeyboard.myapplication.data.remote.dto

data class ForecastItemDto(
    val clouds: CloudsDto,
    val dt: Int,
    val dt_txt: String,
    val main: MainDto,
    val pop: Double,
    val rain: Rain,
    val sys: SysDto,
    val visibility: Int,
    val weather: List<WeatherCoditionDto>,
    val wind: WindDto
)