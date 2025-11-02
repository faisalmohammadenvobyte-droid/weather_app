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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.openkeyboard.myapplication.R
import com.openkeyboard.myapplication.presentation.HomeViewModel
import com.openkeyboard.myapplication.utils.Constants.API_KEY

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
fun HomeScreen(
    modifier: Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
){

    val uiState by homeViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.fetchWeather(lat = 23.60, lon = 90.35, apiKey = API_KEY)
    }

    val stats: List<WeatherStat> = when {
        uiState.isLoading -> emptyList()
        uiState.weather != null -> listOf(
            WeatherStat(Icons.Default.CheckCircle, "UV Index", "7", "High"),
            WeatherStat(Icons.Default.CheckCircle, "Humidity", uiState.weather?.humidity.toString().plus("%"), ""),
            WeatherStat(Icons.Outlined.CheckCircle, "Precipitation", "4mm", "")
        )
        uiState.error != null -> emptyList()
        else -> emptyList()
    }

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
        HomeHeader(modifier,navController)
        CurrentWeatherInfo(navController, uiState)
        Spacer(Modifier.height(16.dp))
        WeatherStats(uiState = uiState, stats = stats)
        Spacer(Modifier.height(16.dp))
        ForecastSection(forecasts)
    }
}
