package com.openkeyboard.myapplication.data.remote.dto

data class City(
    val coord: CoordDto,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)