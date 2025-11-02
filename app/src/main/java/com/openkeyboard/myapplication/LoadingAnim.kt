package com.openkeyboard.myapplication

import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgress() {
    rememberInfiniteTransition()

    CircularProgressIndicator(modifier = Modifier
        .size(50.dp),
        color = Color(0xFF6155F5),
        strokeWidth = 5.dp)
}