package com.openkeyboard.myapplication.utils.location.service

import android.Manifest
import android.content.Context
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
fun isLocationServiceOn(context: Context): Boolean{

    val locationServiceFlow = remember(context) {
        context.observeLocationServiceState()
    }

    // Collect the flow as a State.
    // We use our helper function to get the correct initial value.
    val isLocationServiceOn by locationServiceFlow.collectAsState(
        initial = isLocationServiceEnabled(context)
    )
    return isLocationServiceOn
}