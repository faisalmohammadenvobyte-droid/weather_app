package com.openkeyboard.myapplication.utils.location.service

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun LocationFeatureScreen() {
    val context = LocalContext.current

    // --- 1. Observe Permission State ---
    var hasLocationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    // ... (Your permissionLauncher and showRationaleDialog code) ...

    // --- 2. Observe Location Service State ---
    // We 'remember' the flow so it doesn't get recreated on recomposition
    val locationServiceFlow = remember(context) {
        context.observeLocationServiceState()
    }

    // Collect the flow as a State.
    // We use our helper function to get the correct initial value.
    val isLocationServiceOn by locationServiceFlow.collectAsState(
        initial = isLocationServiceEnabled(context)
    )

    // --- 3. Combine Both States in Your UI ---
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            // Case 1: Permission is NOT granted
            !hasLocationPermission -> {
                Text("We need location permission for this feature.")
                Button(onClick = { /* ... your permission request logic ... */ }) {
                    Text("Request Permission")
                }
                // (Don't forget your rationale dialog logic here)
            }

            // Case 2: Permission IS granted, but Location Service is OFF
            !isLocationServiceOn -> {
                Text("Permission granted! Now, please turn on your phone's location.")
                Button(onClick = {
                    // This is the new part!
                    // We send the user to the system settings page.
                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    context.startActivity(intent)
                }) {
                    Text("Open Location Settings")
                }
            }

            // Case 3: Both are ON! You are good to go.
            else -> {
                Text(text = "Permission and Service are ON!")
                // TODO: Show your "Nearby Restaurants" UI
                // You are now safe to request the actual location.
            }
        }
    }
}