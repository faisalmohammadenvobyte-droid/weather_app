package com.openkeyboard.myapplication.utils.location.get

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.openkeyboard.myapplication.utils.location.service.isLocationServiceEnabled
import kotlinx.coroutines.tasks.await

@SuppressLint("MissingPermission") // We are checking permission inside
suspend fun getCurrentLocation(context: Context): Location? {

    // 1. Get the FusedLocationProviderClient
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    // 2. Check for permissions (CRITICAL)
    // Even though you check in the UI, the util function must be safe
    val hasFinePermission = ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    val hasCoarsePermission = ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    if (!hasFinePermission && !hasCoarsePermission) {
        Log.w("LocationUtil", "App does not have location permission.")
        return null
    }

    // 3. Check if location services are enabled
    if (!isLocationServiceEnabled(context)) {
        Log.w("LocationUtil", "Location services are disabled.")
        return null
    }

    // 4. Request the current location
    // We use .await() from the KTX library to turn this callback-based
    // API into a clean suspend function
    return try {
        // Priority.PRIORITY_HIGH_ACCURACY is for GPS.
        // For city-level, use Priority.PRIORITY_BALANCED_POWER_ACCURACY
        val location = fusedLocationClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            // A CancellationToken is used to cancel the request if needed
            CancellationTokenSource().token
        ).await() // <-- This suspends the coroutine until a result is available

        location

    } catch (e: Exception) {
        // This includes SecurityException (if permission is somehow lost)
        // or any other failure in fetching location.
        Log.e("LocationUtil", "Failed to get current location: ${e.message}")
        null
    }
}