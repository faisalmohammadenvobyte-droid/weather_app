package com.openkeyboard.myapplication.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.openkeyboard.myapplication.presentation.WeatherUiState

@Composable
fun CurrentWeatherInfo(
    navController: NavController,
    uiState: WeatherUiState
) {

    when {
        uiState.isLoading -> CircularProgressIndicator()
        uiState.weather != null -> {
            Column(
                modifier = Modifier.fillMaxWidth().clickable{navController.navigate("details_screen")},
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = uiState.weather.city,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFF333333)
                )
                Text(
                    text = "${uiState.weather.temperature}°C",
                    fontSize = 96.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )
                Text(
                    text = uiState.weather.conditionDescription,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF555555)
                )
            }
        }
        uiState.error != null -> Text("Error: ${uiState.error}")
    }


}