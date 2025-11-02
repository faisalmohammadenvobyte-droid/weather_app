package com.openkeyboard.myapplication.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CurrentWeatherInfo(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth().clickable{navController.navigate("details_screen")},
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hyderabad",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF333333)
        )
        Text(
            text = "25°C",
            fontSize = 96.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
        )
        Text(
            text = "Mostly sunny",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF555555)
        )
    }
}