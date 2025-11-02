package com.openkeyboard.myapplication.utils.network

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun getNetworkState(context: Context): ConnectivityObserver.Status {

    val connectivityObserver = remember { NetworkConnectivityObserver(context) }

    // Observe the flow and convert it to a Compose State
    val networkStatus by connectivityObserver.observe()
        .collectAsState(initial = ConnectivityObserver.Status.Unavailable)

    return networkStatus

}