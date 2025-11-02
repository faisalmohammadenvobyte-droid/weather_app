package com.openkeyboard.myapplication.utils.network

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun NetworkTestScreen(){
    val context = LocalContext.current

    // Create an instance of our observer.
    // (In a real app, you would inject this with Hilt/Koin)
    val networkStatus = getNetworkState(context = context)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Your UI can now react to the network status
        when (networkStatus) {
            ConnectivityObserver.Status.Available -> {
                Text(text = "Internet is CONNECTED")

            }
            else -> {
                // This covers Unavailable, Losing, and Lost
                Text(text = "Internet is DISCONNECTED")
            }
        }
    }
}

