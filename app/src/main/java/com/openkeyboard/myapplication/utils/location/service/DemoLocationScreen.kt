package com.openkeyboard.myapplication.utils.location.service

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember


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