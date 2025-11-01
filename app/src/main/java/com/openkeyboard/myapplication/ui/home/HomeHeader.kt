package com.openkeyboard.myapplication.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun HomeHeader(modifier: Modifier) {



    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Good morning",
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFF333333)
            )
            Text(
                text = "Thu, 31 Oct",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF555555)
            )
        }

        // Glassmorphism Menu Button
        Card(
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.3f)
            ),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            IconButton(onClick = { /* TODO */ }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color(0xFF333333)
                )
            }
        }
    }




}