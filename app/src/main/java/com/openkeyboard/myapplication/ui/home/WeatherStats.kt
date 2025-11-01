package com.openkeyboard.myapplication.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun WeatherStats(stats: List<WeatherStat>) {
    // This is the inner glass card for the stats
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 1f)
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            stats.forEach { stat ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = stat.icon,
                        contentDescription = stat.title,
                        tint = Color(0xFF333333)
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = stat.title,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF555550)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = stat.value,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333)
                    )
                    if (stat.description.isNotEmpty()) {
                        Text(
                            text = stat.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF333333),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}