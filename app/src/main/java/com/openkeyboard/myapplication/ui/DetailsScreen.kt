package com.openkeyboard.myapplication.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

data class CalendarDay(
    val date: Date,
    val dayOfWeek: String,
    val isSelected: Boolean = false
)

@Composable
fun DetailsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        Text(
            text = "Details",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        SevenDayCalendarForWeather()
        CurrentWeatherSummary()
        TemperatureGraphPlaceholder()
        ForecastCard()
    }
}

@Composable
fun SevenDayCalendarForWeather() {
    val dayFormat = remember { SimpleDateFormat("E", Locale.getDefault()) }
    val dateFormat = remember { SimpleDateFormat("d", Locale.getDefault()) }

    val today = remember { Calendar.getInstance() }
    var selectedDate by remember { mutableStateOf(today.time) }

    // Create a list of dates: today + next 6 days
    val dates = remember {
        (0 until 7).map { offset ->
            (today.clone() as Calendar).apply { add(Calendar.DAY_OF_YEAR, offset) }.time
        }
    }

    val calendarDays = dates.map { date ->
        CalendarDay(
            date = date,
            dayOfWeek = dayFormat.format(date),
            isSelected = date == selectedDate
        )
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(calendarDays) { day ->
            WeatherDayItem(
                day = day,
                onDayClick = { clickedDate -> selectedDate = clickedDate }
            )
        }
    }
}


@Composable
fun WeatherDayItem(day: CalendarDay, onDayClick: (Date) -> Unit) {
    val dateFormat = remember { SimpleDateFormat("d", Locale.getDefault()) }
    val dateText = dateFormat.format(day.date)

    val dateColor = if (day.isSelected) Color.White else Color.Black
    val background = if (day.isSelected) Color(0xFF007AFF) else Color.Transparent

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onDayClick(day.date) }
    ) {
        Text(
            text = day.dayOfWeek,
            color = Color.DarkGray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(50))
                .background(background)
        ) {
            Text(
                text = dateText,
                color = dateColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun CurrentWeatherSummary() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = "Today, 4PM", fontSize = 16.sp, color = Color.DarkGray)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "19°C",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Box(modifier = Modifier.size(36.dp)) { /* Weather icon placeholder */ }
            }
        }

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray.copy(alpha = 0.2f))
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .clickable {
                    Log.d("DetailsScreen", "Clicked temperature option")
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Temperature", fontSize = 16.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Select Temperature",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun TemperatureGraphPlaceholder() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Temperature Graph Placeholder",
            color = Color.Gray.copy(alpha = 0.6f),
            fontSize = 16.sp
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        val times = listOf("12PM", "1PM", "2PM", "3PM", "4PM", "5PM", "6PM")
        times.forEach { time ->
            Text(
                text = time,
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ForecastCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F4F8)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Text(
            text = "The weather forecast for today is mostly sunny with a mild temperature drop. The high will be around 25°C and the low will be around 19°C. A slight chance of rain is expected in the afternoon.",
            fontSize = 16.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(16.dp)
        )
    }
}
