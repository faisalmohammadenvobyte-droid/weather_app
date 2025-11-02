package com.openkeyboard.myapplication.utils.location.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.LocationManager
import androidx.core.location.LocationManagerCompat
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

fun Context.observeLocationServiceState(): Flow<Boolean> {

    // We use a callbackFlow to bridge the gap between callback-based APIs
    // (like BroadcastReceiver) and Kotlin Flows.
    return callbackFlow {
        // Get the helper function we just created
        val context = this@observeLocationServiceState

        // 1. Create a BroadcastReceiver to listen for location provider changes
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                // When the provider changes, re-check the state and send the new value
                launch {
                    trySend(isLocationServiceEnabled(context))
                }
            }
        }

        // 2. Register the receiver to listen for the intent
        // This is the "magic" that listens for the location toggle
        val filter = IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
        context.registerReceiver(receiver, filter)

        // 3. Send the *initial* value immediately
        trySend(isLocationServiceEnabled(context))

        // 4. When the Flow is cancelled, unregister the receiver
        awaitClose {
            context.unregisterReceiver(receiver)
        }
    }
}

fun isLocationServiceEnabled(context: Context): Boolean {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    // This helper from androidx.core.location is the modern way to check
    return LocationManagerCompat.isLocationEnabled(locationManager)
}