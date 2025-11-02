package com.openkeyboard.myapplication.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.openkeyboard.myapplication.R

// --- Mock Data Classes ---
data class WeatherStat(
    val icon: ImageVector,
    val title: String,
    val value: String,
    val description: String
)

data class Forecast(
    val icon: Painter, // In a real app, this would be an drawable or URL
    val temp: String,
    val day: String
)

@Composable
fun HomeScreen(modifier: Modifier){

    // Mock data for the UI
    val stats = listOf(
        WeatherStat(Icons.Default.CheckCircle, "UV Index", "7", "High"),
        WeatherStat(Icons.Default.CheckCircle, "Humidity", "61%", ""),
        WeatherStat(Icons.Outlined.CheckCircle, "Precipitation", "4mm", "")
    )
    val forecasts = listOf(
        Forecast(painterResource(R.drawable.ic_sunny), "22°", "Friday, 1 Nov"),
        Forecast(painterResource(R.drawable.ic_rain), "19°", "Sunday, 3 Nov"),
        Forecast(painterResource(R.drawable.ic_windy), "25°", "Saturday, 2 Nov"),
        Forecast(painterResource(R.drawable.ic_sunny), "20°", "Monday, 4 Nov")
    )
    //

    Box(Modifier.fillMaxSize().paint(

        painter = painterResource(id = R.drawable.home_background),
        contentScale = ContentScale.FillBounds)
    )

    Column(Modifier.fillMaxSize()
        .padding(16.dp)

    ) {
        HomeHeader(modifier)
        CurrentWeatherInfo()
        Spacer(Modifier.height(16.dp))
        WeatherStats(stats = stats)
        Spacer(Modifier.height(16.dp))
        ForecastSection(forecasts)



    }
}
