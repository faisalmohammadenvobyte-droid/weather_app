package com.openkeyboard.myapplication.utils.location.permission

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.openkeyboard.myapplication.utils.location.service.isLocationServiceEnabled
import com.openkeyboard.myapplication.utils.location.service.observeLocationServiceState


@Composable
fun LocationPermissionScreen() {
    val context = LocalContext.current
    val activity = context as? ComponentActivity
    // This state will hold whether the permission is granted
    var hasLocationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    // State to control the rationale dialog
    var showRationaleDialog by remember { mutableStateOf(false) }


    val locationServiceFlow = remember(context) {
        context.observeLocationServiceState()
    }

    val isLocationServiceOn by locationServiceFlow.collectAsState(
        initial = isLocationServiceEnabled(context)
    )

    // This is the launcher that will request the permission
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            if (isGranted) {
                // Permission Gained
                Log.d("Permission", "Permission Granted")
                hasLocationPermission = true
            } else {
                // Permission Denied
                Log.d("Permission", "Permission Denied")
                hasLocationPermission = false
            }
        }
    )




    // --- The Rationale Dialog ---
    if (showRationaleDialog) {
        AlertDialog(
            onDismissRequest = {
                // Tapping outside the dialog
                showRationaleDialog = false
            },
            title = {
                Text(text = "Enable Location?")
            },
            text = {
                Text(text = "To show you nearby restaurants, this app needs to know your location. We do not store or share this information.")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        // 1. Close the dialog
                        showRationaleDialog = false
                        // 2. Launch the permission request AGAIN
                        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    }
                ) {
                    Text("Continue")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        // Just close the dialog
                        showRationaleDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {

            ! hasLocationPermission ->{
                Text("We need location permission for this feature.")
                Button(onClick = {
                    // This is the core logic
                    val shouldShowRationale = activity?.shouldShowRequestPermissionRationale(
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) ?: false

                    if (shouldShowRationale) {
                        // If user denied once, show RATIONALE
                        Log.d("Permission", "Showing rationale dialog")
                        showRationaleDialog = true
                    } else {
                        // First time, or "Don't Ask Again"
                        Log.d("Permission", "Launching permission request")
                        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    }
                }) {
                    Text("Request Permission")
                }
            }

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

